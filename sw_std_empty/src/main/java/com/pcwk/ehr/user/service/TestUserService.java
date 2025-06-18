/**
 * Package Name : com.pcwk.ehr.user.service <br/>
 * 파일명: TestUserService.java <br/>
 */
package com.pcwk.ehr.user.service;

import com.pcwk.ehr.user.domain.UserDTO;

/**
 * @author user
 *
 */
public class TestUserService extends UserServiceImpl {

	private String userId;

	/**
	 * @param userId
	 */
	public TestUserService(String userId) {
		super();
		this.userId = userId;   
	}

	@Override
	protected void upgradeLevel(UserDTO user) {
		// pcwk04 등급시 예외 발생.
		if (userId.equals(user.getUserId())) {
			throw new TestUserServiceException("예외가 발생 했습니다.\n 사용자 아이디:" + userId);
		}

		super.upgradeLevel(user);
	}

}
