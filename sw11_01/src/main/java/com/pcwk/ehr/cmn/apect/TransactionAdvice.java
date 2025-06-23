package com.pcwk.ehr.cmn.apect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.pcwk.ehr.cmn.PLog;

public class TransactionAdvice implements PLog, MethodInterceptor {

	private PlatformTransactionManager transactionManager;

	public TransactionAdvice() {

	}

	/**
	 * @param transactionManager the transactionManager to set
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object retObj = null;
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			System.out.println("9999999999999999999999999999");
			// 대상메서드 실행
			retObj = invocation.proceed();

			// 정상적으로 작업을 수행하면 commit
			transactionManager.commit(status);
		} catch (Throwable e) {
			// 예외가 발생하면 rollback
			transactionManager.rollback(status);
			throw e;
		}

		return retObj;
	}
}
