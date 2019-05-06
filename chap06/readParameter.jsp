<%@ page contentType="text/html; charset=utf-8" %>
<%@ page errorPage = "/error/viewErrorMessage.jsp" %>
<html>
<head><title>파라미터 출력</title></head>
<body>

<%-- 
name 파라미터가 존재하지 않아 NullPointerException이 발생하면, 
WAS는 자체적으로 에러 페이지를 생성하는 대신 errorPage 속성에 지정한 JSP를 싱행해서 에러 화면을 생성한다. 
--%>
name 파라미터 값: <%= request.getParameter("name").toUpperCase() %>

</body>
</html>