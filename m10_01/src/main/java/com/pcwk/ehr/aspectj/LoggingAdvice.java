/**
 * Package Name : com.pcwk.ehr.aspectj <br/>
 * 파일명 : LoggingAdvice.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-17<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.aspectj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

/**
 * @author user
 *
 */
public class LoggingAdvice {

	Logger log =  LogManager.getLogger(getClass());

	public void logging(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		
		String methodName = signature.getName();//메서드 이름
		log.debug("methodName:"+methodName);
		
		log.debug("┌───────────────────────────┐");
		log.debug("│ ***logging()***           │");
		log.debug("└───────────────────────────┘");
	}
	
	
}
