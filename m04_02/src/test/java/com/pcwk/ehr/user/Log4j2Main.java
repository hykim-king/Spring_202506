/**
 * Package Name : com.pcwk.ehr.user.dao <br/>
 * 파일명: Log4j2Main.java <br/>
 */
package com.pcwk.ehr.user;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Log4j2Main {

	final static Logger log = LogManager.getLogger(Log4j2Main.class);
	
	public static void main(String[] args) {

		log.debug("디버그 메시지");
		log.info("인포 메시지");
		log.warn("경고 메시지");
		log.error("에러 메시지");
	}

}