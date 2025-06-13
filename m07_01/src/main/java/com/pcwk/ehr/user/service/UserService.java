package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;

public class UserService {

	private UserDao userDao;
	//상

	//BASIC -> SILVER 로그인횟수
	public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
	
	//SILVER -> GOLD 추천횟수
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	
	public UserService() {
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public int doSave(UserDTO param) throws SQLException {
		if (null == param.getGrade()) {
			param.setGrade(Level.BASIC);
		}

		return userDao.doSave(param);
	}

//	1. 현재 레벨이 무엇인지 파악하는 로직.
//	2. 등업 조건.
	private boolean canUpgradeLevel(UserDTO user) {
		Level currentLevel = user.getGrade();

		switch (currentLevel) {
		case BASIC:
			return (user.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER);
		case SILVER:
			return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
		case GOLD:
			return false;
		default:
			throw new IllegalArgumentException("Unknown Level: " + currentLevel);
		}
	}

//	3. 다음 단계의 레벨이 무엇이며, 업그레이드를 위한 작업이 무엇인지.
//	4. 작업 결정 요건과 등업.
	protected void upgradeLevel(UserDTO user) {
		if (Level.BASIC == user.getGrade()) {
			user.setGrade(Level.SILVER);
		} else if (Level.SILVER == user.getGrade()) {
			user.setGrade(Level.GOLD);
		}

		userDao.doUpdate(user);
	}

	// 전체 회원을 대상으로 등업
	// ...
	public void upgradeLevels() {
//		사용자 등급 : BASIC, SILVER, GOLD
//		BASIC :  최초 가입 
//		SILVER: 가입후 50회 이상 로그인
//		GOLD  : SILVER이면서 30회 이상 추천
//
//      전체 회원을		
		List<UserDTO> users = userDao.getAll();

//      반복문: 등업 대상자		
		for (UserDTO user : users) {

			if (canUpgradeLevel(user)) {
				upgradeLevel(user);
			}

		}

	}

}
