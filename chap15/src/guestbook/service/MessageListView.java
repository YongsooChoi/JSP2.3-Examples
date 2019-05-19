package guestbook.service;

import java.util.List;

import guestbook.model.Message;

public class MessageListView {

	private List<Message> messageList;
	private int messageTotalCount;
	private int messageCountPerPage;
	private int currentPageNumber;
	private int firstRow;
	private int endRow;
	private int pageTotalCount;
	
	// 생성자 정의
	public MessageListView(List<Message> messageList, int messageTotalCount, int currentPageNumber,
							int messageCountPerPage, int startRow, int endRow) {
		this.messageList = messageList;						// 메시지 목록
		this.messageTotalCount = messageTotalCount;			// 메시지  전체 갯수
		this.currentPageNumber = currentPageNumber;			// 요청 페이지 번호
		this.messageCountPerPage = messageCountPerPage;		// 페이지 당 메시지 출력 갯수
		this.firstRow = firstRow;							// 전체 메시지 중 현재 메시지 목록의 시작 행
		this.endRow = endRow;								// 전체 메시지 중 현재 메시지 목록의 끝 행
		
		calculatePageTotalCount();
	}
	
	// 전체 페이지 수 구하기
	private void calculatePageTotalCount() {
		if(messageTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = messageTotalCount / messageCountPerPage;
			if(messageTotalCount % messageCountPerPage > 0) {
				pageTotalCount++;
			}
		}
	}
	
	public int getMessageTotalCount() {
		return messageTotalCount;
	}
	
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	
	public List<Message> getMessageList() {
		return messageList;
	}
	
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	
	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}
	
	public int getFirstRow() {
		return firstRow;
	}
	
	public int getEndRow() {
		return endRow;
	}
	
	public boolean inEmpty() {
		return messageTotalCount == 0;
	}
}