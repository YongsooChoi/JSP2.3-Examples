<%@ page contentType="text/html; charset=utf-8" %>
<%
	request.setCharacterEncoding("utf-8");	// 읽어올 파라미터의 캐릭터 인코딩 지정
%>
<jsp:useBean id="memberInfo" class="chap08.member.MemberInfo" /> <!-- memberInfo 클래스 객체 생성 및 이름 저장(참조 변수?) -->
<jsp:setProperty name="memberInfo" property="*" /> <!-- 파라미터 값을 memberInfo 자바빈 객체의 프로퍼티 값으로 저장 -->
<jsp:setProperty name="memberInfo" property="password" value="<%= memberInfo.getId() %>" />
<html>
<head><title>가입</title></head>
<body>

<table width="400" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td>아이디</td>
	<td><jsp:getProperty name="memberInfo" property="id" /></td>
	<td>암호</td>
	<td><jsp:getProperty name="memberInfo" property="password" /></td>
</tr>
<tr>
	<td>이름</td>
	<td><jsp:getProperty name="memberInfo" property="name" /></td>
	<td>이메일</td>
	<td><jsp:getProperty name="memberInfo" property="email" /></td>
</tr>
</table>

</body>
</html>
