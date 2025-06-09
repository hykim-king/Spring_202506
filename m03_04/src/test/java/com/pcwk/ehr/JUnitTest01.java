package com.pcwk.ehr;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTest01 {

	@Test
	public void subStractionTest() {
		int x = 14;
		int y = 16;

		int result = y - x;
		System.out.println("result:"+result);
		assertTrue(result == 2);
		
		
	}
	
	@Test
	public void addtionTest() {
		int x = 14;
		int y = 16;		
		
		int result = y + x;
		//기대 값, 실제결과 비교
		assertEquals(30, result);
		
	}

}



