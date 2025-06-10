/**
 * Package Name : com.pcwk.ehr <br/>
 * 파일명: LifeCycleTest.java <br/>
 */
package com.pcwk.ehr;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LifeCycleTest {
//	@Test	테스트 메서드 지정
//	@Before	각 테스트 실행 전 실행됨(setup)
//	@After	각 테스트 실행 후 실행됨(cleanup)
//	@BeforeClass	클래스 로딩 후 1회 실행되는 메서드
//	@AfterforeClass	모든 테스트 끝난 후 1회 실행되는 메서드
//	@Ignore	해당 테스트는 실행하지 않음
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("=============================");
		System.out.println("@BeforeClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		System.out.println("@AfterClass");
		System.out.println("=============================");
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("***************************");
		System.out.println("@Before");		
		System.out.println("***************************");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("***************************");
		System.out.println("@After");			
		System.out.println("***************************");
	}

	@Test
	public void test() {
		System.out.println("--------------------");
		System.out.println("@Test");			
		System.out.println("--------------------");
	}

	@Test
	public void test2() {
		System.out.println("--------------------");
		System.out.println("Test2");			
		System.out.println("--------------------");
	}
}
