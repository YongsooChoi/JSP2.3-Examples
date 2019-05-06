<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.util.Enumeration" %>
<html>
<head><title>application 기본 객체 속성 보기</title></head>
<body>
<%

	//Enumeration<String> getAttributeNames() : 속성의 이름 목록을 구한다.(pageContext 기본 객체는 이 메서드를 제공하지 않는다.)
	Enumeration<String> attrEnum = application.getAttributeNames();	
	while (attrEnum.hasMoreElements()) {
		String name = attrEnum.nextElement();
		// Object getAttribute(String name) : 이름이 name 인 속성의 값을 구한다. 존재하지 않으면 null을 리턴한다.
		Object value = application.getAttribute(name);	
%>
application 속성 : <b><%= name %></b> = <%= value %> <br>
<% 
	}
%>
</body>
</html>