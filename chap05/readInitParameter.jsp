<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.util.Enumeration" %>
<html>
<head><title>초기화 파라미터 읽어오기</title></head>
<body>

초기화 파라미터 목록:
<ul>
<%
	Enumeration<String> initParamEnum = application.getInitParameterNames();
	while (initParamEnum.hasMoreElements()) {
		String initParamName = initParamEnum.nextElement();
%>
<li><%= initParamName %> =
	<%= application.getInitParameter(initParamName) %>
<%
	}
%>
</ul>

<%-- 
application 기본 객체의 웹 앱 초기화 파라미터 관련 메서드

String getInitParameter(String name) 		: 이름이 name인 웹 앱 초기화 파라미터의 값을 읽어온다. 존재하지 않으면 null을 리턴한다.
Enumeration<String> getInitParameterNames()	: 웹 앱 초기화 파라미터의 이름 목록을 리턴한다.

--%>

</body>
</html>