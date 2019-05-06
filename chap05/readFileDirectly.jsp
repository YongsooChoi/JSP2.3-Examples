<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.io.*" %>
<html>
<head><title>절대 경로 사용하여 자원 읽기</title></head>
<body>

<%
	char[] buff = new char[128];
	int len = -1;
	
	String filePath = "C:\\apache-tomcat-9.0.17\\webapps\\chap05\\message\\notice.txt";
	try(InputStreamReader fr = new InputStreamReader(new FileInputStream(filePath), "UTF-8")) {
		while ((len = fr.read(buff)) != -1) {
			out.print(new String(buff, 0, len));
		}
	} catch(IOException ex) {
		out.println("예외 발생: " + ex.getMessage());
	}
%>

</body>
</html>