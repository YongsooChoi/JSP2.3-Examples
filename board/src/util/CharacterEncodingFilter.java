package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	private String encoding;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
	throws IOException, ServletException {
		req.setCharacterEncoding(encoding);	// 요청 캐릭터 인코딩을 설정한다.
		chain.doFilter(req, res);
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");	// 초기화 파라미터를 이용해 사용할 인코딩 설정
		if(encoding == null) {
			encoding = "utf-8";
		}
	}
	
	@Override
	public void destroy() {}

}
