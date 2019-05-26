package filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class NullParameterFilter implements Filter {	//	필터를 사용하기 위해 Filter 인터페이스를 구현

	private String[] parameterNames = null;

	@Override
	public void init(FilterConfig config) throws ServletException {	// parameterNames 초기화 파라미터의 값을 읽어와
		String names = config.getInitParameter("parameterNames");	// 기본값을 저장할 파라미터 목록을
		StringTokenizer st = new StringTokenizer(names, ", ");		// parameterNames 필드에 저장한다.
		parameterNames = new String[st.countTokens()];				

		for(int i = 0; st.hasMoreTokens(); i++) {
			parameterNames[i] = st.nextToken();
		}
	}
	
	// 필터 작업을 수행한다.
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		NullParameterRequestWrapper requestWrapper = 
				new NullParameterRequestWrapper((HttpServletRequest)request);
		requestWrapper.checkNull(parameterNames);

		chain.doFilter(requestWrapper, response);
	}

	@Override
	public void destroy() {}
}

