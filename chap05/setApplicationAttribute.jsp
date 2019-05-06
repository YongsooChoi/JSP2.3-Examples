<%@ page contentType="text/html; charset=utf-8" %>
<%
	String name = request.getParameter("name");		// application 기본 객체에 설정할 속성 이름으로 사용할 파라미터를 읽어온다.
	String value = request.getParameter("value");	// application 기본 객체에 설정할 속성값으로 사용할 파라미터를 읽어온다.
	
	if (name != null && value != null) {			// void setAttribute(String name, Object value)
		application.setAttribute(name, value);		// application 기본 객체에 속성을 설정한다. 파라미터로 전달받은 값을 사용한다.
	}												// 이름이 name인 속성의 값을 value로 지정한다.
%>

<html>
<head><title>application 속성 지정</title></head>
<body>
<%
	if (name != null && value != null) {
%>
application 기본 객체의 속성 설정:
 <%= name %> = <%= value %>
<%
	} else {
%>
application 기본 객체의 속성 설정 안 함
 <%
	}
 %>
</body>
</html>