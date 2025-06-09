/**
 * Package Name : com.pcwk.ehr.user.field <br/>
 * 파일명: Main.java <br/>
 */
package com.pcwk.ehr.user.field;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext("com.pcwk.ehr");
		
		System.out.println("context:"+context);
		
		Car car=context.getBean(Car.class);
		System.out.println("car:"+car);
		
		car.drive();
	}

}
