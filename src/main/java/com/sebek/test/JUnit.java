package com.sebek.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.*;
import org.junit.runners.Parameterized.Parameters;

public class JUnit {

	private Collection collection;

	@BeforeClass
	 public static void oneTimeSetUp() {
	  //one-time initialization code
	 System.out.println("@BeforeClass - oneTimeSetUp");
	 }

	@AfterClass
	 public static void oneTimeTearDown() {
	  //one-time cleanup code
	 System.out.println("@AfterClass - oneTimeTearDown");
	 }

	@Before
	public void setUp() {
		collection = new ArrayList();
		System.out.println("@Before - setUp");
	}

	@After
	public void tearDown() {
		collection.clear();
		System.out.println("@After - tearDown");
	}

	@Test
	public void testEmptyCollection() {
		assertTrue(collection.isEmpty());
		System.out.println("@Test - testEmptyCollection");
	}

	@Test
	public void testOneItemCollection() {
		collection.add("itemA");
		assertEquals(1, collection.size());
		System.out.println("@Test - testOneItemCollection");
	}

//	@Test(expected = ArithmeticException.class)
//	public void divisionWithException() {
//		int i = 1 / 0;
//	}
//
//	@Ignore("Not Ready to Run")
//	@Test
//	public void ignore() {
//		System.out.println("Method is not ready yet");
//	}
//
//	@Test(timeout = 1000)
//	public void infinity() {
//		while (true)
//			;
//	}
//	
//	 private int number;
//	 
//	 public JUnit(int number) {
//	    this.number = number;
//	 }
// 
//	 @Parameters
//	 public static Collection<Object[]> data() {
//	   Object[][] data = new Object[][] { { 1 }, { 2 }, { 3 }, { 4 } };
//	   return Arrays.asList(data);
//	 }
// 
//	 @Test
//	 public void pushTest() {
//	   System.out.println("Parameterized Number is : " + number);
//	 }
// 
}