<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.net.URLDecoder" %>
<html>
<head><title>쿠키 목록</title></head>
<body>
쿠키 목록<br>
<%
	Cookie[] cookies = request.getCookies(); // 웹 브라우저는 요청 헤더에 쿠키를 저장해서 보내며 JSP는 쿠키 값을 읽어온다.
	if(cookies != null && cookies.length > 0) {
		for(int i = 0; i < cookies.length; i++) {
%>
	<%= cookies[i].getName() %> = 
	<%= URLDecoder.decode(cookies[i].getValue(), "utf-8") %><br> <!-- 인코딩 해서 값을 저장했으므로, 디코딩해서 값을 읽는다. -->
<%
		}
	} else {
%>
쿠키가 존재하지 않습니다.
<%
	}
%>
<%-- 주석 --%>
</body>
</html>
<!-- 주석  -->