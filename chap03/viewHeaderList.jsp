<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.util.Enumeration" %>
<html>
<head><title>헤더 목록 출력</title></head>
<body>

<%-- getHeaderNames() : 모든 헤더의 이름을 구한다 --%>

<%
	Enumeration headerEnum = request.getHeaderNames(); 			// getHeaderNames() : 모든 헤더의 이름을 구한다
	while(headerEnum.hasMoreElements()) {
		String headerName = (String)headerEnum.nextElement();
		String headerValue = request.getHeader(headerName);		// getHeader() : 지정한 이름의 헤더 값을 구한다
%>		
<%= headerName %> = <%= headerValue %><br>
<% 
	}
%>


</body>
</html>