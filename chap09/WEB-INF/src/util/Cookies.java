package util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

public class Cookies {
	
	private Map<String, Cookie> cookieMap = new java.util.HashMap<String, Cookie>(); // <쿠키 이름, 쿠키 객체> 쌍 형태로 저장하는 맵을 생성
	
	public Cookies(HttpServletRequest request) { 		// 쿠키 클래스의 생성자
		Cookie[] cookies = request.getCookies();		// 파라미터로 전달받은 request로부터 Cookie 배열을 읽어와
		if(cookies != null) {
			for (int i = 0; i < cookies.length; i++) {	// 각각의 Cookie 객체를 cookieMap에 저장한다.
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
	}
	
	public Cookie getCookie(String name) {
		return	cookieMap.get(name);
	}
	
	public String getValue(String name) throws IOException {
		Cookie cookie = cookieMap.get(name);
		if(cookie == null) {
			return null;
		}
		return URLDecoder.decode(cookie.getValue(), "euc-kr");
	}
	
	public boolean exists(String name) {
		return cookieMap.get(name) != null;
	}
	
	
	// 입력 파라미터에 따른 쿠키 객체를 생성해서 리턴한다.
	public static Cookie createCookie(String name, String value) throws IOException {
		return new Cookie(name, URLEncoder.encode(value, "utf-8"));
	}
	
	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setPath(path);
		cookie.setDomain(domain);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
}
