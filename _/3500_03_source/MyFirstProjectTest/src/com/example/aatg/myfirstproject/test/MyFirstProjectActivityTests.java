/**
 * Copyright 2010-2011 (C) Diego Torres Milano
 */
package com.example.aatg.myfirstproject.test;

import static android.test.MoreAsserts.assertNotContainsRegex;
import static android.test.ViewAsserts.assertOnScreen;
import static android.test.ViewAsserts.assertRightAligned;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aatg.myfirstproject.MyFirstProjectActivity;

/**
 * @author diego
 *
 */
public class MyFirstProjectActivityTests extends
		ActivityInstrumentationTestCase2<MyFirstProjectActivity> {

	private MyFirstProjectActivity mActivity;
	private EditText mMessage;
	private Button mCapitalize;
	private TextView mLink;
	private Instrumentation mInstrumentation;

	
	public MyFirstProjectActivityTests() {
		this("MyFirstProjectActivityTests");
	}

	/**
	 * @param name
	 */
	public MyFirstProjectActivityTests(String name) {
		super(MyFirstProjectActivity.class);
		setName(name);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		// this must be called before getActivity()
		// disabling touch mode allows for sending key events
		setActivityInitialTouchMode(false);
		
		mActivity = getActivity();
		mInstrumentation = getInstrumentation();
		mLink = (TextView)mActivity.findViewById(com.example.aatg.myfirstproject.R.id.link);
		mMessage = (EditText)mActivity.findViewById(com.example.aatg.myfirstproject.R.id.message);
		mCapitalize = (Button)mActivity.findViewById(com.example.aatg.myfirstproject.R.id.capitalize);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testPreConditions() {
		assertNotNull(mActivity);
		assertNotNull(mInstrumentation);
		assertNotNull(mLink);
		assertNotNull(mMessage);
		assertNotNull(mCapitalize);
	}
	
	public void testAlignment() {
		final int margin = 0;
		assertRightAligned(mMessage, mCapitalize, margin);
	}

	public void testUserInterfaceLayout() {
		final int margin = 0;
		final View origin = mActivity.getWindow().getDecorView();
		assertOnScreen(origin, mMessage);
		assertOnScreen(origin, mCapitalize);
		assertRightAligned(mMessage, mCapitalize, margin);
	}
	
	public void testUserInterfaceLayoutWithOtherOrigin() {
		final int margin = 0;
		View origin = mMessage.getRootView();
		assertOnScreen(origin, mMessage);
		origin = mCapitalize.getRootView();
		assertOnScreen(origin, mCapitalize);
		assertRightAligned(mMessage, mCapitalize, margin);
	}
	
	@UiThreadTest
	public void testNoErrorInCapitalization() {
		final String msg = "this is a sample";
		mMessage.setText(msg);
		mCapitalize.performClick();
		final String actual = mMessage.getText().toString();
		final String notExpectedRegexp = "(?i:ERROR)";
		assertNotContainsRegex("Capitalization found error:",
                notExpectedRegexp, actual);
	}

	public void testFollowLink() {
		final Instrumentation inst = getInstrumentation();
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_VIEW);
		intentFilter.addDataScheme("http");
		intentFilter.addCategory(Intent.CATEGORY_BROWSABLE);
		ActivityMonitor monitor = inst.addMonitor(intentFilter, null, false);
		assertEquals(0, monitor.getHits());
		TouchUtils.clickView(this, mLink);
		monitor.waitForActivityWithTimeout(5000);
		assertEquals(1, monitor.getHits());
		inst.removeMonitor(monitor);
	}
	
	private void requestMessageFocus() {
		try {
			runTestOnUiThread(new Runnable() {
				
				public void run() {
					mMessage.requestFocus();
				}
			});
		} catch (Throwable e) {
			fail("Couldn't set focus");
		}
		
		mInstrumentation.waitForIdleSync();
	}
	
	public void testSendKeyInts() {
		requestMessageFocus();
		
		sendKeys(KeyEvent.KEYCODE_H,
				KeyEvent.KEYCODE_E,
				KeyEvent.KEYCODE_E,
				KeyEvent.KEYCODE_E,
				KeyEvent.KEYCODE_Y,
				KeyEvent.KEYCODE_ALT_LEFT,
				KeyEvent.KEYCODE_1,
				KeyEvent.KEYCODE_DPAD_DOWN,
				KeyEvent.KEYCODE_ENTER);
		
		final String expected = "HEEEY!";
		final String actual = mMessage.getText().toString();
		
		assertEquals(expected, actual);
	}
	
	public void testSendKeyString() {
		requestMessageFocus();
		
		sendKeys("H 3*E Y ALT_LEFT 1 DPAD_DOWN ENTER");
		
		final String expected = "HEEEY!";
		final String actual = mMessage.getText().toString();
		
		assertEquals(expected, actual);
	}
	
	public void testSendRepeatedKeys() {
		requestMessageFocus();
		
		sendRepeatedKeys(1, KeyEvent.KEYCODE_H,
				3, KeyEvent.KEYCODE_E,
				1, KeyEvent.KEYCODE_Y,
				1, KeyEvent.KEYCODE_ALT_LEFT,
				1, KeyEvent.KEYCODE_1,
				1, KeyEvent.KEYCODE_DPAD_DOWN,
				1, KeyEvent.KEYCODE_ENTER);
		
		final String expected = "HEEEY!";
		final String actual = mMessage.getText().toString();
		
		assertEquals(expected, actual);
	}
	
	public void testCapitalizationSendingKeys() {
		final String keysSequence = "T E S T SPACE M E";
		
		requestMessageFocus();
		
		sendKeys(keysSequence);
		TouchUtils.clickView(this, mCapitalize);
		final String expected = "test me".toUpperCase();
		final String actual = mMessage.getText().toString();
		assertEquals(expected, actual);
	}

	public void testDummy() {
		assertNotNull(mActivity.getDummy());
	}
}
