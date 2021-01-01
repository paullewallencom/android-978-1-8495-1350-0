/**
 * Copyright 2010-2011 (C) Diego Torres Milano
 */
package com.example.aatg.myfirstproject.test;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;

public class MyContactsActivityTests extends AndroidTestCase {
	private final static String PKG = "com.example.aatg.myfirstproject";
	private final static String ACTIVITY =  PKG + ".MyContactsActivity";
	private final static String PERMISSION = android.Manifest.permission.CALL_PHONE;
	
	public MyContactsActivityTests() {
		this("MyContactsActivityTests");
	}
	
	public MyContactsActivityTests(String name) {
		super();
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testActivityPermission() {
		assertActivityRequiresPermission(PKG, ACTIVITY, PERMISSION);
	}
	
	@Suppress
	public void testActivityPermissionMyWay() {
		final Intent intent = new Intent();
        intent.setClassName(PKG, ACTIVITY);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
	}

	public void testReadingContacts() {
		final Uri URI = ContactsContract.AUTHORITY_URI;
		final String PERMISSION = android.Manifest.permission.READ_CONTACTS;
		assertReadingContentUriRequiresPermission(URI, PERMISSION);
	}

	public void testWritingContacts() {
		final Uri URI = ContactsContract.AUTHORITY_URI;
		final String PERMISSION = android.Manifest.permission.WRITE_CONTACTS;
		assertWritingContentUriRequiresPermission(URI, PERMISSION);
	}
	
    /**
     * Asserts that launching a given activity is protected by a particular permission by
     * attempting to start the activity and validating that a {@link SecurityException}
     * is thrown that mentions the permission in its error message.
     *
     * Note that an instrumentation isn't needed because all we are looking for is a security error
     * and we don't need to wait for the activity to launch and get a handle to the activity.
     *
     * @param packageName The package name of the activity to launch.
     * @param className The class of the activity to launch.
     * @param permission The name of the permission.
     */
	public void assertActivityRequiresPermission(
			String packageName, String className, String permission) {
		final Intent intent = new Intent();
		intent.setClassName(packageName, className);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		try {
			getContext().startActivity(intent);
			fail("expected security exception for " + permission);
		} catch (SecurityException expected) {
			assertNotNull("security exception's error message.", expected.getMessage());
			assertTrue("error message should contain " + permission + ".",
					expected.getMessage().contains(permission));
		}
	}



}
