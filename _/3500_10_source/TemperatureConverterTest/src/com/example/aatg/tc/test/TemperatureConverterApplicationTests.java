/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc.test;

import com.example.aatg.tc.TemperatureConverterApplication;

import android.test.ApplicationTestCase;

/**
 * @author diego
 *
 */
public class TemperatureConverterApplicationTests extends
		ApplicationTestCase<TemperatureConverterApplication> {

	private TemperatureConverterApplication mApplication;
	
	/**
	 * 
	 */
	public TemperatureConverterApplicationTests() {
		this("TemperatureConverterApplicationTests");
	}
	/**
	 * @param name
	 */
	public TemperatureConverterApplicationTests(String name) {
		super(TemperatureConverterApplication.class);
		setName(name);
	}

	/* (non-Javadoc)
	 * @see android.test.ApplicationTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		final RenamingMockContext mockContext = new RenamingMockContext(getContext());
		setContext(mockContext);
		createApplication();
		mApplication = getApplication();
	}

	/* (non-Javadoc)
	 * @see android.test.ApplicationTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testPreconditions() {
		assertNotNull(mApplication);
	}
	
	public final void testSetDecimalPlaces() {
		final int expected = 3;
		mApplication.setDecimalPlaces(expected);
		assertEquals(expected, mApplication.getDecimalPlaces());
	}
}
