<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.net.URLEncoder" %>
<%
	Cookie cookie1 = new Cookie("id", "madvirus");
	cookie1.setDomain(".somehost.com");
	response.addCookie(cookie1);
	
	Cookie cookie2 = new Cookie("only", "onlycookie");
	response.addCookie(cookie2);
	
	Cookie cookie3 = new Cookie("invalid", "invalidcookie");
	cookie3.setDomain("javacan.tistory.com");
	response.addCookie(cookie3);
%>
<html>
<head><title>쿠키 생성</title></head>
<body>

다음과 같이 쿠키를 생성했습니다. <br>
<%= cookie1.getName() %>=<%= cookie1.getValue() %>"
[<%= cookie1.getDomain() %>]
<%= cookie2.getName() %>=<%= cookie2.getValue() %>"
[<%= cookie2.getDomain() %>]
<%= cookie3.getName() %>=<%= cookie3.getValue() %>"
[<%= cookie3.getDomain() %>]
<%-- 주석 --%>
</body>
</html>
<!-- 주석  -->