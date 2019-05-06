<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.io.*" %>
<%@ page import = "java.net.URL" %>
<html>
<head><title>application 기본 객체 사용하여 자원 읽기2</title></head>
<body>

<%
	String resourcePath = "/message/notice.txt";
	char[] buff = new char[128];
	int len = -1;
	// java.net.URL getResource(String path) : 웹 앱 내에서 지정한 경로에 해당하는 자원에 접근할 수 있는 URL 객체를 리턴한다.
	URL url = application.getResource(resourcePath);
	try(InputStreamReader br = new InputStreamReader(url.openStream(), "UTF-8")) {
		while ((len = br.read(buff)) != -1) {
			out.print(new String(buff, 0, len));
		}
	} catch(IOException ex) {
		out.println("익셉션 발생: " + ex.getMessage());
	}
		
	// URL url = application.getResource(resourcePath);
	// InputStream is = url.openStream();
	//
	// 위 코드는 아래 코드와 동일하다
	// 
	// InputStream is = application.getResourceAsStream(resourcePath);
	
%>

</body>
</html>