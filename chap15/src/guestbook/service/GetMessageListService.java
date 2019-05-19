package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import guestbook.dao.MessageDao;
import guestbook.model.Message;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class GetMessageListService {
	
	// 싱글톤 패턴을 적용한다.
	private static GetMessageListService instance = new GetMessageListService();
		public static GetMessageListService getInstance() {
		return instance;
	}
		private GetMessageListService() {
	}
	
	// 한 페이지에서 보여줄 메시지 객수를 3개로 지정한다.
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	
	public MessageListView getMessageList(int pageNumber) {
		Connection conn = null;
		int currentPageNumber = pageNumber;
		try {
			conn = ConnectionProvider.getConnection();
			MessageDao messageDao = MessageDao.getInstance();
			
			// 전체 메시지 갯수를 구한다.
			int messageTotalCount = messageDao.selectCount(conn);
			
			List<Message> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			if(messageTotalCount > 0) {	// 요청한 페이지 pageNumber에 속하는 메시지의 시작 행과 끝 행을 구한다.
				firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				messageList = messageDao.selectList(conn, firstRow, endRow); // 시작과 끝 행에 속하는 메시지 목록을 구한다 
			} else {
				currentPageNumber = 0;
				messageList = Collections.emptyList(); // 메시지 갯수가 0인 경우 빈 List를 할당한다.
			}
			return new MessageListView(messageList, messageTotalCount, currentPageNumber,
										MESSAGE_COUNT_PER_PAGE, firstRow, endRow); // 객체 리턴
		} catch (SQLException e) {
			throw new ServiceException("목록 구하기 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
