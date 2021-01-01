/**
 * 
 */
package com.example.aatg.tc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.aatg.tc.EditNumber;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * @author diego
 *
 */
@RunWith(RobolectricTestRunner.class)
public class EditNumberTests {

	private static final double DELTA = 0.00001d;
	private EditNumber mEditNumber;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mEditNumber = new EditNumber(null);
		mEditNumber.setFocusable(true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testPreconditions() {
		assertNotNull(mEditNumber);
	}
	
	/**
	 * Test method for {@link com.example.aatg.tc.EditNumber#EditNumber(android.content.Context, android.util.AttributeSet, int)}.
	 */
	@Test
	public final void testEditNumberContextAttributeSetInt() {
        final EditNumber e = new EditNumber(null, null, -1);
		assertNotNull(e);
	}

	/**
	 * Test method for {@link com.example.aatg.tc.EditNumber#clear()}.
	 */
	@Test
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
	@Test
	public final void testSetNumber() {
		mEditNumber.setNumber(123.45);
		final String expected = "123.45";
		final String actual = mEditNumber.getText().toString();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.example.aatg.tc.EditNumber#getNumber()}.
	 */
	@Test
	public final void testGetNumber() {
		mEditNumber.setNumber(123.45);
		final double expected = 123.45;
		final double actual = mEditNumber.getNumber();
		assertEquals(expected, actual, DELTA);
	}

}
