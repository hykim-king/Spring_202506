package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;

public class UserService {
	Logger log = LogManager.getLogger(getClass());

	private PlatformTransactionManager transactionManager;

	private UserDao userDao;
	// 상

	// BASIC -> SILVER 로그인횟수
	public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;

	// SILVER -> GOLD 추천횟수
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;

	// 트랜잭션 동기화 적용
	private DataSource dataSource;

	public UserService() {
	}

	/**
	 * @param transactionManager the transactionManager to set
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
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
	public void upgradeLevels() throws SQLException {

		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
		
			List<UserDTO> users = userDao.getAll();
		
			for (UserDTO user : users) {

				if (canUpgradeLevel(user)) {
					upgradeLevel(user);
				}

			}

			// 정상적으로 작업을 수행하면 commit
			transactionManager.commit(status);
		} catch (Exception e) {
			log.debug("***************************");
			log.debug("*Exception conn.rollback():" + e.getMessage());
			log.debug("***************************");
			// 예외가 발생하면 rollback
			transactionManager.rollback(status);
			throw e;
		}

	}

}
