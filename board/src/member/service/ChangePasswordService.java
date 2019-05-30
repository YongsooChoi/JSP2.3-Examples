package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {
	
	private MemberDao memberDao = new MemberDao();
	
	// 암호 변경 기능을 구현한다.
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// userId에 해당하는 Member 데이터를 구한다.
			Member member = memberDao.selectById(conn, userId);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			if(!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			member.changePassword(newPwd);	// member 객체의 암호 데이터를 변경한다.
			memberDao.update(conn, member);	// 변경한 암호 데이터를 DB 테이블에 반영한다.
			conn.commit();
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
