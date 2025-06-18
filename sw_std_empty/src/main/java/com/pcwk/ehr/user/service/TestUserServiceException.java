/**
 * Package Name : com.pcwk.ehr.user.service <br/>
 * 파일명: TestUserServiceException.java <br/>
 */
package com.pcwk.ehr.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestUserServiceException extends RuntimeException {
	Logger log = LogManager.getLogger(getClass());
	
	public TestUserServiceException(String meString) {
		super(meString);
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ TestUserServiceException()                              │"+meString);
		log.debug("└─────────────────────────────────────────────────────────┘");
		
	}
}
