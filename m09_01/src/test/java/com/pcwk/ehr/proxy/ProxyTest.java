/**
 * Package Name : com.pcwk.ehr.proxy <br/>
 * 파일명: ProxyTest.java <br/>
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

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class ProxyTest {
	Logger log = LogManager.getLogger(getClass());
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
//		log.debug("┌─────────────────────────────────────────────────────────┐");
//		log.debug("│ setUp()                                                 │");
//		log.debug("└─────────────────────────────────────────────────────────┘");		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
//		log.debug("┌─────────────────────────────────────────────────────────┐");
//		log.debug("│ tearDown()                                              │");
//		log.debug("└─────────────────────────────────────────────────────────┘");		
	}
	
	@Test
	void dynamicProxy() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *dynamicProxy()*          │");
		log.debug("└───────────────────────────┘");
		
		//다이내믹 프록시
		//런타임시 동적으로 만들어 지는 오브젝트
		//리플렉션을 이용해서 프록시 생성
		
		Hello dynamicPrxiedHello = (Hello) Proxy.newProxyInstance(
				getClass().getClassLoader(),  //동적으로 구성되는 다이나믹 프록시 클래스 로딩 
				new Class[] {Hello.class},    //구현할 인터페이스
				new UppercaseHandler(new HelloTarget())//부가기능과 위임 코드를 담은 InvocationHandler 
				);
		
		log.debug(dynamicPrxiedHello.sayHello("Pcwk"));
		log.debug(dynamicPrxiedHello.sayHi("Pcwk"));
		log.debug(dynamicPrxiedHello.sayThankYou("Pcwk"));
		
		assertEquals("HELLO PCWK", dynamicPrxiedHello.sayHello("Pcwk"));
		assertEquals("HI PCWK", dynamicPrxiedHello.sayHi("Pcwk"));
		assertEquals("Thank You Pcwk", dynamicPrxiedHello.sayThankYou("Pcwk"));		
	}
	
	@Disabled
	@Test
	void simpleProxy() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *simpleProxy()*           │");
		log.debug("└───────────────────────────┘");
		
		Hello hello = new HelloTarget();
		
		log.debug(hello.sayHello("Pcwk"));
		log.debug(hello.sayHi("Pcwk"));
		log.debug(hello.sayThankYou("Pcwk"));
	}

	@Disabled	
	@Test
	void upperProxy() {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *upperProxy()*            │");
		log.debug("└───────────────────────────┘");
		
		Hello proxyHello =new HelloUpperCase(new HelloTarget());
		log.debug(proxyHello.sayHello("Pcwk"));
		log.debug(proxyHello.sayHi("Pcwk"));
		log.debug(proxyHello.sayThankYou("Pcwk"));
		
		assertEquals("HELLO PCWK", proxyHello.sayHello("Pcwk"));
		assertEquals("HI PCWK", proxyHello.sayHi("Pcwk"));
		assertEquals("THANK YOU PCWK", proxyHello.sayThankYou("Pcwk"));
	}
	
	

}
