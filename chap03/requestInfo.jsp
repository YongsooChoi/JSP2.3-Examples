<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head><title>클라이언트 및 서버 정보</title></head>
<body>

클라이언트IP = <%= request.getRemoteAddr() %><br>			<%-- 웹 서버에 연결한 클라이언트의 IP주소를 구한다 --%>
요청정보길이 = <%= request.getContentLength() %><br>			<%-- 클라이언트가 전송한 요청 정보의 길이를 구한다 --%>
요청정보 인코딩 = <%= request.getCharacterEncoding() %><br>	<%-- 클라이언트가 요청 정보를 전송할 때 사용한 캐릭터의 인코딩을 구한다 --%>
요청정보 컨텐츠타입 = <%= request.getContentType()%><br>		<%-- 클라이언트가 요청 정보를 전송할 때 사용한 컨텐츠의 타입을 구한다 --%>
요청정보 프로토콜 = <%= request.getProtocol() %><br>			<%-- 클라이언트가 요청한 프로토콜을 구한다 --%>
요청정보 전송방식 = <%= request.getMethod() %><br>				<%-- 웹 브라우저가 정보를 전송할 때 사용한 방식을 구한다 --%>
요청 URI = <%= request.getRequestURI() %><br> 			<%-- 웹 브라우저가 요청한 URL에서 경로를 구한다 --%>
컨텍스트 경로 = <%= request.getContextPath() %><br>			<%-- JSP 페이지가 속한 웹 앱의 컨텍스트 경로를 구한다 --%>	
서버이름 = <%= request.getServerName() %><br>				<%-- 연결할 때 사용한 서버 이름을 구한다 --%>
서버포트 = <%= request.getServerPort() %><br>				<%-- 서버가 실행중인 포트 번호를 구한다 --%>

</body>
</html>