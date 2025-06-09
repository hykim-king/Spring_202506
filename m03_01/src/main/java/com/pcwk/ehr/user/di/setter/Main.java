/**
 * Package Name : com.pcwk.ehr.user.di.setter <br/>
 * 파일명: Main.java <br/>
 */
package com.pcwk.ehr.user.di.setter;


public class Main {

	public static void main(String[] args) {
		Engine engine=new Engine();
		
		Car car=new Car();
		car.setEngine(engine); //setter Injection
		
		car.drive();

	}

}
