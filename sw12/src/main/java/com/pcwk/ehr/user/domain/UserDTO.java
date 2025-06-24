package com.pcwk.ehr.user.domain;

import com.pcwk.ehr.cmn.DTO;

public class UserDTO extends DTO{
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
	
	//--------------------------------------추가
	private int      login    ;// 로그인
	private int      recommend;// 추천 
	private Level    grade    ;// 등급 
	private String   email    ;// 이메일

	
	public UserDTO() {}



	/**
	 * @param userId
	 * @param name
	 * @param password
	 * @param regDt
	 * @param login
	 * @param recommend
	 * @param grade
	 * @param email
	 */
	public UserDTO(String userId, String name, String password, String regDt, int login, int recommend, Level grade,
			String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.regDt = regDt;
		this.login = login;
		this.recommend = recommend;
		this.grade = grade;
		this.email = email;
	}



	/**
	 * @return the login
	 */
	public int getLogin() {
		return login;
	}



	/**
	 * @param login the login to set
	 */
	public void setLogin(int login) {
		this.login = login;
	}



	/**
	 * @return the recommend
	 */
	public int getRecommend() {
		return recommend;
	}



	/**
	 * @param recommend the recommend to set
	 */
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}



	/**
	 * @return the grade
	 */
	public Level getGrade() {
		return grade;
	}



	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Level grade) {
		this.grade = grade;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
		return "UserDTO [userId=" + userId + ", name=" + name + ", password=" + password + ", regDt=" + regDt
				+ ", login=" + login + ", recommend=" + recommend + ", grade=" + grade + ", email=" + email
				+ ", toString()=" + super.toString() + "]";
	}




}
