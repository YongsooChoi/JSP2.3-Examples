package filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

@WebFilter(filterName = "xsltFilter", urlPatterns = {"/xml/*"})
public class XSLTFilter implements Filter {

	private String xslPath = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		xslPath = config.getServletContext().getRealPath("/WEB-INF/xsl/book.xsl"); // 변환할 때 사용할 XSL 파일의 경로를 구한다.
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
		response.setContentType("text/html; charset=utf-8"); // 필터가 생성할 출력의 콘텐트 타입을 지정. 웹 브라우저에 출력되는 문서는 HTML 문서가 된다.
		PrintWriter writer = response.getWriter();
		XSLTResponseWrapper responseWrapper = new XSLTResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request, responseWrapper);
		
		// XSL/T 변환
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Reader xslReader = new BufferedReader(new FileReader(xslPath));
			
			StreamSource xslSource = new StreamSource(xslReader);
			
			Transformer transformer = factory.newTransformer(xslSource);
			
			String xmlDocument = responseWrapper.getBufferString(); // 응답 래퍼로부터 JSP/서블릿이 생성한 내용을 읽어온다. xml문서원본으로 사용한다.
			Reader xmlReader = new StringReader(xmlDocument);
			StreamSource xmlSource = new StreamSource(xmlReader);
			
			StringWriter buffer = new StringWriter(4096);
			transformer.transform(xmlSource, new StreamResult(buffer)); // XSL/T 변환을 실행한다.
			
			writer.print(buffer.toString()); // 변환 결과를 출력한다.
		} catch(Exception ex) {
			throw new ServletException(ex);
		}
		
		writer.flush();
		writer.close();
	}
	
	@Override
	public void destroy() {}
}
