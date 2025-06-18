/**
 * Package Name : com.pcwk.ehr.cmn.apect <br/>
 * 파일명 : PerformanceAdvice.java <br/>
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
package com.pcwk.ehr.cmn.apect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.pcwk.ehr.cmn.PLog;

/**
 * @author user
 *
 */
//@Aspect
public class PerformanceAdvice implements PLog {

	// Service에서 성능 측정
	//@Around("execution(* com.pcwk.ehr..service.*Impl.*(..) )")
	public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

		Object retObj = null;
		long start = System.currentTimeMillis();

		// 클래스 명
		String className = pjp.getTarget().getClass().getName();

		// 메서드 명
		String methodName = pjp.getSignature().getName();

		// 대상메서드 실행
		retObj = pjp.proceed();

		long end = System.currentTimeMillis();

		long executionTime = end - start;
		log.debug("│ className          │" + className);
		log.debug("│ methodName         │" + methodName);
		log.debug("^^^^^executionTime: " + executionTime + "ms");

		return retObj;
	}

}
