<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>numberFormat 태그 사용</title></head>
<body>

<c:set var="now" value="<%= new java.util.Date() %>" />
<fmt:formatDate value="${now}" type="date" dateStyle="full" /> <br>
<fmt:formatDate value="${now}" type="date" dateStyle="short" /> <br>
<fmt:formatDate value="${now}" type="time" /> <br>
<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full" /> <br>
<fmt:formatDate value="${now}" pattern="z a h:mm" />

<%--

문자열로 표시된 날짜와 시간 값을 java.util.Date로 파싱해 주는 <fmt:parseDate> 사용 예

<fmt:parseDate value="2009-03-01 13:00:59" pattern="yyyy-MM-dd HH:mm:ss" var="date" />
${date}
 
--%>
 
</body>
</html>
