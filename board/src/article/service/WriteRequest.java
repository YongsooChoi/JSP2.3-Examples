package article.service;

import java.util.Map;

import article.model.Writer;

public class WriteRequest {
	
	// 게시글을 스는데 필요한 작성자, 제목, 내용 데이터를 정의한다.
	private Writer writer;
	private String title;
	private String content;
	
	public WriteRequest(Writer writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	public Writer getWriter() {
		return writer;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	public void validate(Map<String, Boolean> errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}

