<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>

<html>
<head><title>회원 목록</title></head>
<body>

MEMBER 테이블의 내용
<table width="100%" border="1">
<tr>
	<td>이름</td><td>아이디</td><td>이메일</td>
</tr>
<%
	// 1. JDBC 드라이버 로딩
	Class.forName("com.mysql.jdbc.Driver");
	
	
//	try { // 지정한 클래스 정보를 담고 있는 Class 인스턴스를 구해주는 기능. 클래스는 메서드를 통해 로딩될 때 자동으로 JDBC 드라이버로 등록.
//		Class.forName("JDBC 드라이버 클래스의 완전한 이름");	
//	} catch(ClassNotFoundException ex) { 			
//		// 지정한 클래스가 존재하지 않는 경우 에러가 발생한다. 에러 처리.
//	}
//
//	MySQL : com.mysql.jdbc.Driver
//	오라클: oracle.jdbc.driver.OracleDriver
//	MS SQL서버 : com.microsoft.sqlserver.jdbc.SQLServerDriver

 
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		String jdbcDriver = "jdbc:mysql://localhost:3307/chap14?" +
							"useUnicode=true&characterEncoding=utf8";
		String dbUser = "jspexam";
		String dbPass = "jsppw";
		
		String query = "select * from MEMBER order by MEMBERID";
		
		// 2. 데이터베이스 커넥션 생성
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		
		// 3. Statement 생성
		stmt = conn.createStatement();
		
		// 4. 쿼리 실행
		rs = stmt.executeQuery(query);
		
		// 5. 쿼리 실행 결과 출력
		while(rs.next()) {
%>
<tr>
	<td><%= rs.getString("NAME") %></td>
	<td><%= rs.getString("MEMBERID") %></td>
	<td><%= rs.getString("EMAIL") %></td>
</tr>
<%
		}
	} catch(SQLException ex) {
		out.println(ex.getMessage());
		ex.printStackTrace();
	} finally {
		
		// 6. 사용한 Statement 종료
		if(rs != null) {
			try { 
				rs.close();
			} catch(SQLException ex) {
				
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch(SQLException ex) {
				
			}
		}
		
		// 7. 커넥션 종료
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException ex) {
				
			}
		}
		
	}
%>
</table>

</body>
</html>