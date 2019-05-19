<!-- 
	writeMessage.jsp는 <jsp:useBean> 액션 태그를 이용해서 사용자가 입력한 값을 Message 객체에 저장하고,
	WriteMessageService.write() 메서드를 이용해서 Message 객체의 값을 저장한다.
 -->

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "guestbook.model.Message" %>
<%@ page import = "guestbook.service.WriteMessageService" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="message" class="guestbook.model.Message">	<!-- 메시지 객체를 생성하고 -->
	<jsp:setProperty name="message" property="*"/>			<!-- 요청 파라미터의 값을 객체의 프로퍼티에 저장 -->
</jsp:useBean>
<%
	WriteMessageService writeService = WriteMessageService.getInstance();
	writeService.write(message);	// 사용자가 입력한 정보를 저장
%>
<html>
<head>
	<title>방명록 메시지 남김</title>
</head>
<body>
방명록에 메시지를 남겼습니다. <br/>
<a href="list.jsp">[목록 보기]</a>
</body>
</html>