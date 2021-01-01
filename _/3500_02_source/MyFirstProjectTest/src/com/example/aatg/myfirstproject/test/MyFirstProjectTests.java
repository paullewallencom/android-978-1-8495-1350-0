/**
 * Copyright 2010-2011 (C) Diego Torres Milano
 */
package com.example.aatg.myfirstproject.test;

import android.os.Debug;
import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;

/**
 * @author diego
 *
 */
public class MyFirstProjectTests extends TestCase {

	private static final boolean DEBUG = false;

	public MyFirstProjectTests() {
		this("MyFirstProjectTests");
	}

	/**
	 * @param name
	 */
	public MyFirstProjectTests(String name) {
		super(name);
		
		if ( DEBUG ) {
			Debug.waitForDebugger();
		}
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testSomething() {
		fail("Not implemented yet");
	}
	
	@VeryImportantTest
	public void testOtherStuff() {
		fail("Not implemented yet");
	}
}
