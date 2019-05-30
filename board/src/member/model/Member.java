/*
 * member 테이블의 데이터를 담을 때 사용할 Member 클래스
 */

package member.model;

import java.util.Date;

public class Member {
	
	private String id;
	private String name;
	private String password;
	private Date regDate;
	
	public Member(String id, String name, String password, Date regDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	// 암호 변경 기능을 구현할 때 사용한다.
	public boolean matchPassword(String pwd) {	
		return password.equals(pwd);
	}
	
	// 암호 변경 기능
	public void changePassword(String newPwd) {
		this.password = newPwd;
	}
}
