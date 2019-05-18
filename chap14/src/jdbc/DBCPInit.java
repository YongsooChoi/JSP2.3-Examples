/* 
 * Connection Pool을 초기화하기 위한 서블릿
 */

package jdbc;

import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");	// 커넥션 풀이 내부에서 사용할 JDBC 드라이버 로딩
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool() {
		try {
			String jdbcUrl = "jdbc:mysql://localhost:3307/chap14?useUnicode=true&characterEncoding=utf8";
			String username = "jspexam";
			String pw = "jsppw";
			
			// 커넥션 풀이 새로운 커넥션을 생성할 때 사용할 커넥션 팩토리를 생성
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			
			// PoolableConnectioin을 생성하는 팩토리를 생성. (DBCP는 커넥션 풀에 커넥션을 보관할 때 PoolableConnection을 사용한다.
			// 이 클래스는 내부적으로 실제 커넥션을 담고 있으며, 커넥션 풀을 관리하는 데 필요한 기능을 추가로 제공한다.
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1"); // 커넥션이 유효한지 여부를 검사할 때 사용할 쿼리를 지정
			
			// 커넥션 풀의 설정 정보를 생성
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);	// 유휴 커넥션 검사 주기
			poolConfig.setTestWhileIdle(true);	// 풀에 보관 중인 커넥션이 유효한지 검사할지 여부
			poolConfig.setMinIdle(4);			// 커넥션 최소 개수 지정
			poolConfig.setMaxTotal(50);			// 커넥션 최대 개수 지정
			
			// 커넥션 풀을 생성. 생성자는 PoolableConnection을 생성할 때 사용할 팩토리와 커넥션 풀 설정을 파라미터로 받는다.
			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);	// 생성한 커넥션 풀을 PoolableConnectionFactory에 연결 
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");	// 커넥션 풀을 제공하는 JDBC 드라이버 등록
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			// 커넥션 풀 드라이버에 생성한 커넥션 풀을 등록. 풀 이름 "chap14" 이 경우 프로그램에서 사용하는 JDBC URL은 "jdbc:apache:commons:dbcp:chap14" 
			driver.registerPool("chap14", connectionPool);	
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
