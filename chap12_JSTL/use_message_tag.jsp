<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <fmt:setLocale value="en"/> --%>		<%-- 웹 브라우저의 언어 설정에 상관없이 <fmt:bundle> 태그가 사용하는 리소스 번들의 로케일을 변경할 때 --%>

<fmt:bundle basename="resource.message">	<%-- resource.message 리소스 번들을 기본으로 사용하도록 지정한다 --%>
<fmt:message key="TITLE" var="title" />		<%-- 키값이 TITLE인 메시지를 변수 title에 저장한다 --%>

<html>
<head><title>${title}</title></head>
<body>

<fmt:message key="GREETING" />
<br>
<c:if test="${!empty param.id}">
<fmt:message key="VISITOR">
	<fmt:param value="${param.id}" />
</fmt:message>
</c:if>

</body>
</html>
</fmt:bundle>