/**
 * Package Name : com.pcwk.ehr.user.field <br/>
 * 파일명: Engine.java <br/>
 */
package com.pcwk.ehr.user.field;

import org.springframework.stereotype.Component;

@Component
public class Engine {

	public Engine() {}
	
	public void start() {
		System.out.println("Engine 시동");
	}
}
