<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head><title>pageContext 기본객체</title></head>
<body>

<% 
	HttpServletRequest httpRequest = 
	(HttpServletRequest)pageContext.getRequest();	
%> <%-- pageContext.getRequest()의 리턴 타입은 ServletRequest인데, JSP페이지가  HTTP 요청을 처리하므로 HttpServletRequest로 타입 변환  --%>

request 기본 객체와 pageContext.getRequest()의 동일여부:

<%= request == httpRequest %> <%-- request 기본 객체와  pageContext.getRequest()의 리턴 값이 같은 객체인지 검사--%>

<br>

pageContext.getOut() 메서드를 사용한 데이터 출력:

<% pageContext.getOut().println("안녕하세요!"); %> <%-- pageContext.getOut.println()은 out.println()과 동일 --%>

<%-- 
pageContext가 제공하는 기본 객체 접근 메서드

ServletRequest getRequest()			: request 기본 객체를 구한다. (실제 리턴 객체 타입은 HttpServletRequest)
ServletResponse getResponse()		: response 기본 객체를 구한다. (실제 리턴 객체 타입은 HttpServletResponse)
HttpSession getSession()			: session 기본 객체를 구한다.
ServletContext getServletContext()	: application 기본 객체를 구한다.
ServletConfig getServletConfig()	: config 기본 객체를 구한다.
JspWriter getOut()					: out 기본 객체를 구한다.
Exception getException()			: exception 기본 객체를 구한다. (JSP 페이지가 에러 페이지인 경우에만 의미가 있다.)
Object getPage()					: page 기본 객체를 구한다.

--%>


</body>
</html>