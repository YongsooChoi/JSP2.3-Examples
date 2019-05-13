<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="util.FormatUtil" %>
<%
	request.setAttribute("price", 12345L);
%>
<html>
<head><title>EL 함수 호출2</title></head>
<body>

가격은 <b>${FormatUtil.number(price,'#,##0')}</b> 입니다. <!-- 접두어가 'elfunc'이며, 호출하는 함수는 formatNumber이다. -->

</body>
</html>