<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page buffer="8kb" autoFlush="false" %>	<%-- 버퍼가 다 찼을 때 자동으로 플러시하지 않는다 --%>
<html>
<head><title>버퍼 정보</title></head>
<body>

<%-- out 기본 객체의 버퍼 관련 메서드 --%>

버퍼 크기: <%= out.getBufferSize() %> <br>		<%-- 버퍼의 크기를 구한다 (int) --%>
현재 버퍼의 남은 크기: <%= out.getRemaining() %> <br>		<%-- 현재 버퍼의 남은 크기를 구한다 (int) --%>
auto flush: <%= out.isAutoFlush() %> <br> 	<%-- 버퍼가 다 찾을 때 자동으로 플러시 할 경우 true 를 리턴한다 (boolean) --%>

<%-- 
void clear()		: 버퍼의 내용을 비운다. 만약 버퍼를 이미 플러시했다면 IOException을 발생시킨다.
void clearBuffer()	: 버퍼의 내용을 비운다. clear()메서드와 달리 버퍼를 플러시한 경우에도 예외를 발생시키지 않는다.
void flush()		: 버퍼를 플러시한다. 즉 버퍼의 내용을 클라이언트에 전송한다.
 --%>

</body>
</html>