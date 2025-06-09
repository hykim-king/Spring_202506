/**
 * Package Name : com.pcwk.ehr.user.di <br/>
 * 파일명: Car.java <br/>
 */
package com.pcwk.ehr.user.di;

/**
 * @author user
 *
 */
public class Car {

	private Engine engine;

	/**
	 * @param engine
	 */
	public Car(Engine engine) {
		super();
		this.engine = engine;
	}
	
	
	public void drive() {
		engine.start();
		System.out.println("Car가 달립니다.");
	}
	
}
