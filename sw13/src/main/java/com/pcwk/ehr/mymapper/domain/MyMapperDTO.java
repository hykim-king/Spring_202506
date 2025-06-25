package com.pcwk.ehr.mymapper.domain;

import com.pcwk.ehr.cmn.DTO;

public class MyMapperDTO extends DTO {

	private String userId;
	private String password;
	
	public MyMapperDTO() {}

	/**
	 * @param userId
	 * @param password
	 */
	public MyMapperDTO(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
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

	@Override
	public String toString() {
		return "MyMapperDTO [userId=" + userId + ", password=" + password + "]";
	}
	
	
	
}
