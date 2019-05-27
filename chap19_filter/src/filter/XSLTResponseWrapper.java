package filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class XSLTResponseWrapper extends HttpServletResponseWrapper {
	
	private ResponseBufferWriter buffer = null;
	
	public XSLTResponseWrapper(HttpServletResponse response) {
		super(response);
		buffer = new ResponseBufferWriter();
	}
	
	// JSP/서블릿은 ServletResponse의 getWriter() 메서드로 구한 출력 스트림에 데이터를 출력한다. 
	@Override
	public PrintWriter getWriter() throws java.io.IOException {
		return buffer;
	}
	
	// JSP/서블릿에서 설정한 콘텐트 타입을 무시하고 필터에서 새롭게 콘텐트 타입을 지정한다.
	@Override
	public void setContentType(String contentType) {
		// do nothing
	}
	
	// 버퍼에 저장된 내용을 구한다. 즉 JSP나 서블릿이 생성한 내용을 리턴한다.
	public String getBufferString() {
		return buffer.toString();
	}
}
