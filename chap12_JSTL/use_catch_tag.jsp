<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>cathc 태그</title></head>
<body>

<c:catch var="ex">
name 파라미터의 값 =<%= request.getParameter("name") %> <br>
<%  
	if(request.getParameter("name").equals("test")) { // name 파라미터가 존재하지 않을 경우 NullPointerException이 발생
%>
${param.name}은 test 입니다.
<%
	}
%>
</c:catch>	<!-- 익셉션 발생시 ex 변수에 익셉션 객체를 저장한다 -->
<p>
<c:if test="${ex != null}">
익셉션이 발생했습니다. <br>
${ex}
</c:if>

</body>
</html>
