/**
 * Package Name : com.pcwk.ehr.user.di.setter <br/>
 * 파일명: Car.java <br/>
 */
package com.pcwk.ehr.user.di.setter;

public class Car {

	private Engine engine;
	
	public Car() { }

	/**
	 * @param engine the engine to set
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public void drive() {
		engine.start();
		System.out.println("Car가 달립니다.");
	}
	
}
