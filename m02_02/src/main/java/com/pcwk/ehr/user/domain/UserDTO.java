package com.pcwk.ehr.user.domain;

public class UserDTO {
	//세로편집 :    Alt + shift +A
	//소문자 변환 :  Alt + shift +Y
	//대문자 변환 :  Alt + shift +X
	
	//UserDTO
	//-------------------------------
	//전역변수
	//Default 생성자
	//인자있는 생성자
	//get/setters
	//toString()
	
	
	private String  userId   ;//사용자ID
	private String  name     ;//이름
	private String  password ;//비밀번호
	private String  regDt    ;//등록일
	
	public UserDTO() {}

	/**
	 * @param userId
	 * @param name
	 * @param password
	 * @param regDt
	 */
	public UserDTO(String userId, String name, String password, String regDt) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.regDt = regDt;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", password=" + password + ", regDt=" + regDt + "]";
	}
	
	
	
}
