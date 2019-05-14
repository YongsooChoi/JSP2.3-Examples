<%@ page contentType="text/html; charset=utf-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:url value="http://search.daum.net/search" var="searchUrl">	<!-- var 속성, scope 속성 생략 가능 -->
	<c:param name="w" value="blog" />							<!-- var 속성 지정하지 않으면 현재 위치에 생성한 URL을 출력 -->
	<c:param name="q" value="공원" />								<!-- 파라미터를 URL에 추가할 수 있다 -->
</c:url>

<!-- 

value: 읽어올 URL
var: 읽어온 결과를 저장할 변수 이름
scope: 변수를 저장할 영역

-->

<ul>
	<li>${searchUrl}</li>
	<li><c:url value="/use_if_tag.jsp" /> </li>		<!-- 상대 URL 웹 앱 내에서의 절대 경로, 슬래시로 시작: URL에 컨택스트 경로 추가  -->
	<li><c:url value="./use_if_tag.jsp" /> </li> 	<!-- 상대 URL 현재 JSP 에 대한 상대 경로, 슬래시로 시작하지 않음 -->
</ul>

</body>
</html>
