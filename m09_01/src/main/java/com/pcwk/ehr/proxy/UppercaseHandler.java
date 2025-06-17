/**
 * Package Name : com.pcwk.ehr.proxy <br/>
 * 파일명: UppercaseHandler.java <br/>
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
package com.pcwk.ehr.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author user
 *
 */
public class UppercaseHandler implements InvocationHandler {
	Logger log = LogManager.getLogger(getClass());

	private Hello target;

	public UppercaseHandler(Hello target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *invoke()*                │");
		log.debug("└───────────────────────────┘");
		
		log.debug("method:{}",method.getName());
		log.debug("args:{}",args.toString());
		
		//메서드 호출
		Object ret=method.invoke(target, args);
		log.debug("ret:{}",ret);
		
		
		//sayH로 시작되는 메서드에만 부가 기능 적용: sayThankYou 미적용
		//PointCut
		if(ret instanceof  String  &&  method.getName().startsWith("sayH")) {
			return ((String)ret).toUpperCase();
		}else {
			return ret;
		}

	}

}
