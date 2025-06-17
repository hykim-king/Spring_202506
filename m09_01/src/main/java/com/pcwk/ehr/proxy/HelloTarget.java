/**
 * Package Name : com.pcwk.ehr.proxy <br/>
 * 파일명: HelloTarget.java <br/>
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

/**
 * @author user
 *
 */
public class HelloTarget implements Hello {

	@Override
	public String sayHello(String name) {
		return "Hello "+name;
	}

	@Override
	public String sayHi(String name) {
		return "Hi "+name;
	}

	@Override
	public String sayThankYou(String name) {
		return "Thank You "+name;
	}

}
