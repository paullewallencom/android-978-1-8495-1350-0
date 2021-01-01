/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc.test;

import static org.easymock.EasyMock.cmp;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.Comparator;

import org.easymock.EasyMock;
import org.easymock.LogicalOperator;
import org.hamcrest.integration.EasyMock2Adapter;
import org.hamcrest.object.HasToString;

import android.test.AndroidTestCase;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.aatg.tc.EditNumber;

/**
 * @author diego
 *
 */
public class EditNumberTests extends AndroidTestCase {

	/**
	 * This constant is used to select between Hamcrest matchers or EasyMock comparator
	 */
	private static final boolean USE_HAMCREST = true;
	
	private EditNumber mEditNumber;

	/**
	 * Constructor
	 */
	public EditNumberTests() {
		this("EditNumberTests");
	}
	
	/**
	 * Constructor
	 * 
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
	 * Test method for {@link com.example.aatg.tc.EditNumber#EditNumber(android.content.Context, android.util.AttributeSet)}.
	 */
	public final void testEditNumberContextAttributeSetInt() {
		EditNumber en = new EditNumber(mContext, null, 0);
		assertNotNull(en);
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

	/**
	 * Test method for {@link com.example.aatg.tc.EditNumber}. Several input strings are
	 * set and compared against the expected results after filters are applied.
	 * This test use {@link EasyMock}
	 */
	public final void testTextChanged() {
		final String[] sai = new String[] {
				null, "", "1", "123", "-123", "0", "1.2", "-1.2", "1-2-3",
                "+1", "1.2.3" };
		final String[] sar = new String[] {
				"",   "", "1", "123", "-123", "0", "1.2", "-1.2", "123",
                "1", "12.3" };

		// mock
		final TextWatcher watcher = createMock(TextWatcher.class);
		mEditNumber.addTextChangedListener(watcher);

		for (int i=1; i < sai.length; i++) {
			if ( !USE_HAMCREST ) {
				// record
				watcher.beforeTextChanged(stringCmp(sar[i-1]), eq(0),
						eq(sar[i-1].length()), eq(sar[i].length()));
				watcher.onTextChanged(stringCmp(sar[i]), eq(0),
						eq(sar[i-1].length()), eq(sar[i].length()));
				watcher.afterTextChanged(stringCmp(
						Editable.Factory.getInstance().newEditable(sar[i])));
			}
			else {
				// record
				watcher.beforeTextChanged(hasToString(sar[i-1]), eq(0),
						eq(sar[i-1].length()), eq(sar[i].length()));
				watcher.onTextChanged(hasToString(sar[i]), eq(0),
						eq(sar[i-1].length()), eq(sar[i].length()));
				watcher.afterTextChanged(hasToString(
						Editable.Factory.getInstance().newEditable(sar[i])));
			}
			

			// replay
			replay(watcher);

			// exersise
			mEditNumber.setText(sai[i]);

			// test
			final String actual = mEditNumber.getText().toString();
			assertEquals(sai[i] + " => " + sar[i] + " => " + actual, sar[i], actual);

			// verify
			verify(watcher);

			// reset
			reset(watcher);
		}
	}
	
	public static final class StringComparator<T> implements Comparator<T> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 *
		 * Return the {@link String} comparison of the arguments.
		 */
		@Override
		public int compare(T object1, T object2) {
			return object1.toString().compareTo(object2.toString());
		}        
	}
	
	/**
	 * Return {@link EasyMock.cmp} using a {@link StringComparator} and
	 * {@link LogicalOperator.EQUAL}
	 *
	 * @param <T> The original class of the arguments
	 * @param o The argument to the comparison
	 * @return {@link EasyMock.cmp}
	 */
	public static <T> T stringCmp(T o) {
		return cmp(o, new StringComparator<T>(), LogicalOperator.EQUAL);
	}
	
    /**
     * Create an {@link EasyMock2Adapter} using a
     * {@link HasToString.hasToString}
     *
     * @param <T> The original class of the arguments
     * @param o The argument to the comparison
     * @return o
     */
	public static <T> T hasToString(T o) {
		EasyMock2Adapter.adapt(
				HasToString.hasToString(o.toString()));
		return o;
	}
}
