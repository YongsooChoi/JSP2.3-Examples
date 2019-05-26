/*
 * 지정한 파라미터가 존재하지 않을 경우, 파라미터의 값을 공백문자열("")로 제공하는 요청 래피 클래스 작성
 */

package filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

// 요청 래퍼 클래스로 동작하기 위해 HttpServletRequestWrapper 클래스를 상속받는다
public class NullParameterRequestWrapper extends HttpServletRequestWrapper { 
	
	private Map<String, String[]> parameterMap = null;
	
	// 생성자는 전달받은 request의 파라미터 정보를 parameterMap에 저장한다.
	public NullParameterRequestWrapper(HttpServletRequest request) {
		super(request);
		parameterMap = new HashMap<String, String[]>(request.getParameterMap());
	}
	
	// 검사할 파라미터의 이름 목록을 인자로 전달 받아 검사해서 존재하지 않으면 빈 문자열을 저장한다.
	public void checkNull(String[] parameterNames) {
		for(int i = 0; i < parameterNames.length; i++) {
			if(!parameterMap.containsKey(parameterNames[i])) {
				String[] values = new String[]{""};
				parameterMap.put(parameterNames[i], values);
			}
		}
	}
	
	// 파라미터와 관련된 메서드를 구현해서 parameterMap에서 파라미터 값을 얽어오도록 한다.
	@Override
	public String getParameter(String name) {
		String[] values = getParameterValues(name);
		if(values != null && values.length > 0)
			return values[0];
		return null;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(parameterMap.keySet());
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return (String[])parameterMap.get(name);
	}
}
