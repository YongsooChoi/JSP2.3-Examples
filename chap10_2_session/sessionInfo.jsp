<%@ page contentType="text/html; charset=utf-8" %>
<%@ page session="true" %>								<!-- 기본값이 true 이므로 생략 가능 -->
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date time = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<html>
<head><title>세션정보</title></head>
<body>
세션ID: <%= session.getId() %> <br>
<%
	time.setTime(session.getCreationTime());
%>
세션생성시간: <%= formatter.format(time) %> <br>
<%
	time.setTime(session.getLastAccessedTime());
%>
최종접근시간: <%= formatter.format(time) %>

</body>
</html>
