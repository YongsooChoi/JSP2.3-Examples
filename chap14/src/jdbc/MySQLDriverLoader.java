package jdbc;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class MySQLDriverLoader extends HttpServlet {
	// 서블릿을 초기화할 때 호출되는 init() 메서드를 구현
	public void init(ServletConfig config) throws ServletException {	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception ex) {
			throw new ServletException(ex);
		}
	}
}
