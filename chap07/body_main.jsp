<%@ page contentType="text/html; charset=utf-8" %>
<%
	request.setCharacterEncoding("utf-8");	// <jsp:param>으로 전달되는 값은 request.setCharacterEncoding()에 명시한 캐릭터셋으로 인코딩해서 전달한다.
%>
<html>
<head><title>INFO</title></head>
<body>

include 전 name 파라미터 값: <%= request.getParameter("name") %>

<hr>

<jsp:include page="body_sub.jsp" flush="false">
	<jsp:param name="name" value="최범균" />
</jsp:include>

<hr/>

include 후 name 파라미터 값: <%= request.getParameter("name") %>

</body>
</html>
