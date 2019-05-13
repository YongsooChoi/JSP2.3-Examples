<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="elfunc" uri="/WEB-INF/tlds/el-functions.tld" %>	<!-- 함수를 정의한 태그 라이브러리를 지정. 접두어를 'elfunc'로 지정 -->
<%
	request.setAttribute("price", 12345);
%>
<html>
<head><title>EL 함수 호출</title></head>
<body>

오늘은 <b>${elfunc:formatNumber(price,'#,##0')}</b> 입니다. <!-- 접두어가 'elfunc'이며, 호출하는 함수는 formatNumber이다. -->

</body>
</html>