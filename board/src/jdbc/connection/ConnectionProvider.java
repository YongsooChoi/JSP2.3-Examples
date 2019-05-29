/*
 * 커넥션을 구할 때 사용하는 자바 소스
 */

package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
	}
}
