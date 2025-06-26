package com.pcwk.ehr.user.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.mapper.UserMapper;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserDTO;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	Logger log = LogManager.getLogger(getClass());

	@Qualifier("dummyMailService") // bean id로 특정 빈 주입
	@Autowired
	private MailSender mailSender;

	@Autowired
	private UserMapper mapper;

	public UserServiceImpl() {
	}

	private void sendUpgradeEmail(UserDTO user) {
		// 보내는 사람
		// 받는 사람
		// 제목
		// 내용
		System.setProperty("https.protocols", "TLSv1.2");
		System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			// 보내는 사람
			message.setFrom("jamesol@naver.com");

			// 받는 사람
			message.setTo(user.getEmail());

			// 제목 : 등업 안내
			message.setSubject("등업 안내");

			// 내용: {}님의 등급이 {GOLD}로 등업되었습니다.
			String contents = user.getName() + "님의 등급이 " + user.getGrade() + "로 등업 되었습니다.";

			log.debug(contents);
			message.setText(contents);

			mailSender.send(message);
		} catch (Exception e) {
			log.debug("┌───────────────────────────┐");
			log.debug("│ *sendUpgradeEmail.Exception()*   │" + e.getMessage());
			log.debug("└───────────────────────────┘");
			e.printStackTrace();
		}
	}

	@Override
	public List<UserDTO> doRetrieve(SearchDTO param) {
		return mapper.doRetrieve(param);
	}

	@Override
	public int doDelete(UserDTO param) {
		return mapper.doDelete(param);
	}

	@Override
	public int doUpdate(UserDTO param) {
		return mapper.doUpdate(param);
	}

	@Override
	public UserDTO doSelectOne(UserDTO param) throws SQLException {
		return mapper.doSelectOne(param);
	}

	@Override
	public int doSave(UserDTO param) throws SQLException {
		if (null == param.getGrade()) {
			param.setGrade(Level.BASIC);
		}

		return mapper.doSave(param);
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

		mapper.doUpdate(user);

		// mail전송
		sendUpgradeEmail(user);

	}

	// 전체 회원을 대상으로 등업
	// ...
	@Override
	public void upgradeLevels() throws SQLException {

		List<UserDTO> users = mapper.getAll();

		for (UserDTO user : users) {

			if (canUpgradeLevel(user)) {
				upgradeLevel(user);
			}

		}

	}

}
