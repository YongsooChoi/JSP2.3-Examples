/*
 * JoinService가 회원 가입 기능을 구현할 때 필요한 요청 데이터를 담는 클래스
 */

package member.service;

import java.util.Map;

public class JoinRequest {
	
	private String id;	// 회원 가입 기능을 구현할 때 필요한 요청 데이터를 보관하는 필드
	private String name;
	private String password;
	private String confirmPassword;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	// password 필드와 confirmPassword 필드값이 같은 지 검사한다. 필드 데이터가 유효한 지 여부를 검사할 때 사용한다. 
	public boolean isPasswordEqualToConfirm() {	
		return password != null && password.equals(confirmPassword);
	}
	
	// 각 필드의 데이터가 유효한지 검사한다. errors 맵 객채는 에러 정보를 담기 위해 사용한다. 올바르지 않은 경우 TRUE 값을 추가한다.
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		if(!errors.containsKey("confirmPassword")) {
			if(!isPasswordEqualToConfirm()) {	// 암호와 확인값이 일치하지 않으면 notMatch라는 에러 키를 추가한다.
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	
	// value가 값이 없는 경우 errors 맵 객체의 fieldName 키에 TRUE를 값으로 추가한다.
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}
