<%@ page contentType="text/html; charset=utf-8" %>
<%@ page isErrorPage = "true" %>	<%-- JSP를 에러 페이지로 지정한다 --%>> 
<html>
<head><title>에러 발생</title></head>
<body>

요청 처리 과정에서 에러가 발생했습니다. <br>
빠른 시간 내에 문제를 해결하도록 하겠습니다. 
<p>
에러 타입: <%= exception.getClass().getName() %> <br>	<%-- exception 기본 객체의 클래스 이름 출력 --%>
에러 메세지: <b><%= exception.getMessage() %></b>		<%-- exception 기본 객체의 메세지 출력 --%>

</body>
</html>
<!-- 주석  -->