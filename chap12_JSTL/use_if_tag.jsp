<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>if 태그</title></head>
<body>
<c:if test="true">
무조건 수행<br>
</c:if>

<c:if test="${param.name == 'bk'}">
name 파라미터의 값이 ${param.name} 입니다. <br>
</c:if>

<c:if test="${param.age > 18}">
당신의 나이는 18세 이상입니다.
</c:if>
</body>
</html>

<!-- 
<c:out> 태그의 속성
value: 출력할 값
excapeXml: 특수 문자를 변환할 지의 여부
default: value의 결과값이 null인 경우 출력할 값

 -->