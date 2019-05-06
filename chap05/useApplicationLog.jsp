<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head><title>로그 메시지 기록</title></head>
<body>

<%
	application.log("로그 메시지 기록");
%>
로그 메시지를 기록합니다.

<%-- 
application 기본 객체가 제공하는 로그 기록 메서드
void log(String msg) 						: msg를 로그로 남긴다.
void log(String msg, Throwable throwable)	: msg를 로그로 남긴다. 예외 정보도 함께 로그에 기록한다.

 --%>>

</body>
</html>