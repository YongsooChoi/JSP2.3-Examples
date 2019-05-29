package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {
	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from member where memberid = ?");
			pstmt.setString(1,  id);
			rs = pstmt.executeQuery();
			Member member = null;
			if(rs.next()) {	// member 테이블에 memberid 칼럼 값이 id 파라미터와 동일한 데이터가 존재하면 Member 객체를 생성
				member = new Member(
						rs.getString("memberid"),
						rs.getString("name"),
						rs.getString("password"),
						toDate(rs.getTimestamp("regdate")));	// JDBC에서 날짜시간 타입은 Timestamp이고,
			}													// Member에서 사용한 타입은 Date이기에 변환.
			return member;	// 데이터가 존재하지 않으면 null을 리턴
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Date toDate(Timestamp date) {		// 날짜시간 타입 변환 메서드 정의
		return date == null ? null : new Date(date.getTime());
	}
	
	public void insert(Connection conn, Member mem) throws SQLException {	// member 테이블에 데이터 추가
		try(PreparedStatement pstmt = conn.prepareStatement("insert into member values(?,?,?,?)")) {
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime()));
			pstmt.executeUpdate();
		}
	}
}
