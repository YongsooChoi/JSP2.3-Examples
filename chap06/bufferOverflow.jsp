<%@ page contentType="text/html; charset=utf-8" %>
<%@ page buffer="1kb" %>
<%@ page errorPage = "/error/viewErrorMessage.jsp" %>
<html>
<head><title>버퍼 플러시 이후 에러 발생 결과</title></head>
<body>

<% 
	for(int i = 0; i < 300; i++) { out.println(i); }
// 주석	
%>

<%= 1 / 0 %>
<%-- 주석 --%>
</body>
</html>
<!-- 주석  -->