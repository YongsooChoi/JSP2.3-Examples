package guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import org.apache.tomcat.jdbc.pool.JdbcInterceptor;

import guestbook.model.Message;
import jdbc.JdbcUtil;

public class MessageDao {
	
	// 한 개의 객체만 사용하도록 싱글톤 패턴을 적용
	private static MessageDao messageDao = new MessageDao();	// 유일한 객체를 정적 필드에 저장
	public static MessageDao getInstance() {					// 유일한 객체에 접근할 수 있는 정적 메서드 정의
		return messageDao;
	}
	private MessageDao() {}										// 생성자를 private으로 설정해서 외부에서 접근하지 못함
	
	// insert 쿼리를 이용해서 message 파라미터 데이터를 guestbook_message 테이블에 삽입
	// message_id 칼럼은 auto_increment이므로 설정하지 않음
	public int insert(Connection conn, Message message) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into guestbook_message " + 
											"(guest_name, password, message) values (?, ?, ?)");
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	// guestbook_message에서 message_id 칼럼의 값이 messageId인 레코드를 조회
	public Message select(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from guestbook_message where message_id = ?");
			pstmt.setInt(1, messageId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return makeMessageFromResultSet(rs);	// 레코드가 존재하면 메서드를 이용해  Message 객체를 생성
			} else {
				return null;							// 존재하지 않으면 null 리턴
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// ResultSet 데이터를 읽어와 Message를 생성 (select() 메서드와 selectList() 메서드에서 사용)
	private Message makeMessageFromResultSet(ResultSet rs) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("message_id"));
		message.setGuestName(rs.getString("guest_name"));
		message.setPassword(rs.getString("password"));
		message.setMessage(rs.getString("message"));
		return message;
	}
	
	// 테이블 전체 레코드 수를 구한다 
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from guestbook_message");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	// 테이블의 데이터를 message_id 내림차순으로 정렬해서 조회
	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from guestbook_message " +		// 페이징 구현에 사용
											"order by message_id desc limit ?, ?"); // limit [시작 행 번호],[읽어올 개수]
			pstmt.setInt(1, firstRow - 1);
			pstmt.setInt(2, endRow - firstRow + 1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Message> messageList = new ArrayList<Message>();
				do {
					messageList.add(makeMessageFromResultSet(rs));
				} while (rs.next());
				return messageList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 테이블에서 message_id 칼럼 값이 messageId인 레코드를 삭제
	public int delete(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete from guestbook_message where message_id = ?");
			pstmt.setInt(1, messageId);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close
			(pstmt);
		}
	}
}
