/*
 * 회원 가입 기능을 제공
 */

package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();	// DB 커넥션을 구하고, 
			conn.setAutoCommit(false);					// 트랜잭션을 시작한다.
			
			Member member = memberDao.selectById(conn, joinReq.getId()); // memberDao의 메서드를 이용해서 해당하는 회원 데이터를 구한다.
			if(member != null) {	// 가입하려는 ID에 해당하는 데이터가 이미 존재하면
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			
			memberDao.insert(conn, // joinReq를 이용해서 Member 객체를 생성하고, MemberDao의 insert()를 실행해서 회원 데이터를 테이블에 삽입
					new Member(
							joinReq.getId(),
							joinReq.getName(),
							joinReq.getPassword(),
							new Date())
					);
			conn.commit();	// 트랜잭션 커밋
		} catch(SQLException e) {	// 처리 도중 익셉션 발생하면 트랜잭션 롤백, RuntimeException 발생시킨다.
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);	// 커넥션을 종료한다.
		}
	}
	
}
