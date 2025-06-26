package com.pcwk.ehr.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class DummyMailService implements MailSender {
	Logger log = LogManager.getLogger(getClass());

	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *DummyMailService*        │");
		log.debug("│ 개발에서는 메일이 전송되지 않습니다.  │");
		log.debug("└───────────────────────────┘");

	}

	@Override
	public void send(SimpleMailMessage... simpleMessages) throws MailException {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *DummyMailService*        │");
		log.debug("│ 개발에서는 메일이 전송되지 않습니다.  │");
		log.debug("└───────────────────────────┘");

	}

}
