/**
 * Package Name : com.pcwk.ehr.user.dao <br/>
 * 파일명 : DaoFactory.java <br/>
 */
package com.pcwk.ehr.user.dao;

public class DaoFactory {

	/**
	 * UserDao생성
	 * 
	 * @return UserDao
	 */
	public UserDao userDao() {

		UserDao dao = new UserDao(connectionMaker());

		return dao;
	}

	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}

}
