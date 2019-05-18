<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%
	String idValue = request.getParameter("id");

	Connection conn = null;
	PreparedStatement pstmtItem = null;
	PreparedStatement pstmtDetail = null;
	
	String jdbcDriver = "jdbc:mysql://localhost:3307/chap14?useUnicode=true&characterEncoding=utf8";
	String dbUser = "jspexam";
	String dbPass = "jsppw";
	
	Throwable occuredException = null;
	
	try {
		int id = Integer.parseInt(idValue);
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		conn.setAutoCommit(false);	// 트랜잭션을 시작한다
		
		pstmtItem = conn.prepareStatement("insert into ITEM values (?, ?)");
		pstmtItem.setInt(1, id);
		pstmtItem.setString(2, "상품 이름 " + id);
		pstmtItem.executeUpdate();
		
		if(request.getParameter("error") != null) {	// 첫 번째 쿼리 실행후 error 파라미터 값이 null이 아니면
			throw new Exception("의도적 익셉션 발생");		// 익셉션을 발생시켜서 두 번째 쿼리를 실행하지 않는다.
		}
		
		pstmtDetail = conn.prepareStatement("insert into ITEM_DETAIL values (?, ?)");
		pstmtDetail.setInt(1, id);
		pstmtDetail.setString(2, "상세 설명 " + id);
		pstmtDetail.executeUpdate();
		
		conn.commit();
	} catch(Throwable e) {
		if(conn != null) {
			try {
				conn.rollback();
			} catch(SQLException ex) {}
		}
		occuredException = e;
	} finally {
		if(pstmtItem != null) try { pstmtItem.close(); } catch(SQLException ex) {}
		if(pstmtDetail != null) try { pstmtDetail.close(); } catch(SQLException ex) {}
		if(conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
%>
<html>
<head><title>ITEM 값 입력</title></head>
<body>

<% if(occuredException != null) { %>
에러가 발생: <%= occuredException.getMessage() %>
<% } else { %>
커밋 성공
<% } %>

</body>
</html>