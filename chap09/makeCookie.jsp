<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.net.URLEncoder" %>
<%
	Cookie cookie = new Cookie("name", URLEncoder.encode("최범균","utf-8")); // URLEncoder 클래스를 사용해 쿠키 값을 인코딩한다
	response.addCookie(cookie); // 응답 데이터에 쿠키를 추가한다.
%>
<html>
<head><title>쿠키 생성</title></head>
<body>

<%= cookie.getName() %> 쿠키의 값 = "<%= cookie.getValue() %>"
<%-- 주석 --%>
</body>
</html>
<!-- 주석  -->