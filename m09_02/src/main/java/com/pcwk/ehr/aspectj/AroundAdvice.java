/**
 * Package Name : com.pcwk.ehr.aspectj <br/>
 * 파일명 : AroundAdvice.java <br/>
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

import org.aspectj.lang.ProceedingJoinPoint;

import com.pcwk.ehr.cmn.PLog;

/**
 * @author user
 *
 */
public class AroundAdvice implements PLog {

	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		Object retObj = null;
		log.debug("┌───────────────────────────┐");
		log.debug("│ ***aroundLog()***         │");

		// 클래스 명
		String className = pjp.getTarget().getClass().getName();

		// 메서드 명
		String methodName = pjp.getSignature().getName();

		retObj = pjp.proceed();

		log.debug("│ className         │" + className);
		log.debug("│ methodName         │" + methodName);
		log.debug("└───────────────────────────┘");

		return retObj;
	}

}
