<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.io.*" %>
<html>
<head><title>기본 객체 사용하여 자원 읽기</title></head>
<body>

<%
	String resourcePath = "/message/notice.txt";	// 웹 앱 내에서의 경로를 사용한다.
%>
자원의 실제 경로:<br>
<%= application.getRealPath(resourcePath) %><br>	<%-- 웹 앱 내에서 지정한 경로에 해당하는 시스템 상의 자원의 실제 경로를 구한다. --%>
-----------<br>
<%= resourcePath %>의 내용<br>
-----------<br>
<%
	char[] buff = new char[128];
	int len = -1;
	
	// 자원으로부터 데이터를 읽어오는 스트림을 생성한다.
	// java.io.InputStream getResourceAsStream(String path) : 웹 앱 내에서 지정한 경로에 해당하는 자원으로부터 데이터를 읽어올 수 있는 InputStream을 리턴한다.
	try(InputStreamReader br = new InputStreamReader(	
			application.getResourceAsStream(resourcePath), "UTF-8")) {	
		while((len = br.read(buff)) != -1) {			// 스트림으로부터 데이터를 읽어와 출력한다.
			out.print(new String(buff, 0, len));
		}
	} catch (IOException ex) {
		out.println("예외 발생: " + ex.getMessage());
	}
%>

</body>
</html>