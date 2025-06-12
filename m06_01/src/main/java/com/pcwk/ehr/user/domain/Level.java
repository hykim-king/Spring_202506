/**
 * Package Name : com.pcwk.ehr.user.domain <br/>
 * 파일명: Level.java <br/>
 */
package com.pcwk.ehr.user.domain;

/**
 * @author user
 *
 */
public enum Level {

	BASIC(1), SILVER(2), GOLD(3);
	
	private final int value;

	/**
	 * @param value
	 */
	Level(int value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	//값으로 부터 Level 오브젝트 return
	public static Level valueOf(int value) {
		
		switch(value) {
		case 1:
			return BASIC;
		case 2:
			return SILVER;			
		case 3:
			return GOLD;		
		default:
			throw new AssertionError("Unknown value:"+value);
			
		}
	}
	
	
	
	
}
