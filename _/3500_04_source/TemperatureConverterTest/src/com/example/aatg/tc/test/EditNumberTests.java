/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc.test;

import android.test.AndroidTestCase;

import com.example.aatg.tc.EditNumber;

/**
 * @author diego
 *
 */
public class EditNumberTests extends AndroidTestCase {

	private EditNumber mEditNumber;

	public EditNumberTests() {
		this("EditNumberTests");
	}
	
	/**
	 * @param name
	 */
	public EditNumberTests(String name) {
		setName(name);
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		mEditNumber = new EditNumber(mContext);
		mEditNumber.setFocusable(true);
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.example.aatg.tc.EditNumber#EditNumber(android.content.Context)}.
	 */
	public final void testEditNumberContext() {
		assertNotNull(mEditNumber);
	}

	/**
	 * Test method for {@link com.example.aatg.tc.EditNumber#clear()}.
	 */
	public final void testClear() {
		final String value = "123.45";
		mEditNumber.setText(value);
		mEditNumber.clear();
		String expectedString = "";
		String actualString = mEditNumber.getText().toString();
		assertEquals(expectedString, actualString);
	}

	/**
	 * Test method for {@link com.example.aatg.tc.EditNumber#setNumber(double)}.
	 */
	public final void testSetNumber() {
		mEditNumber.setNumber(123.45);
		final String expected = "123.45";
		final String actual = mEditNumber.getText().toString();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.example.aatg.tc.EditNumber#getNumber()}.
	 */
	public final void testGetNumber() {
		mEditNumber.setNumber(123.45);
		final double expected = 123.45;
		final double actual = mEditNumber.getNumber();
		assertEquals(expected, actual);
	}

}
