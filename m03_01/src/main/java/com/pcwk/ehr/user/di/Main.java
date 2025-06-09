/**
 * Package Name : com.pcwk.ehr.user.di <br/>
 * 파일명: Main.java <br/>
 */
package com.pcwk.ehr.user.di;

public class Main {

	public static void main(String[] args) {
		Engine engine=new Engine();
		
		Car car=new Car(engine);
		car.drive();
	}

}
