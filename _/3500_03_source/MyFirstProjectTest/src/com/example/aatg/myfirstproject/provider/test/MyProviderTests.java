/**
 * Copyright 2010-2011 (C) Diego Torres Milano
 */
package com.example.aatg.myfirstproject.provider.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.suitebuilder.annotation.Suppress;

import com.example.aatg.myfirstproject.provider.MyProvider;

public class MyProviderTests extends ProviderTestCase2<MyProvider> {

	private MyProvider mProvider;

	public MyProviderTests() {
		this("MyProviderTests");
	}
	
	public MyProviderTests(String name) {
		super(MyProvider.class, MyProvider.AUTHORITY);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		setContext(getMockContext());
		mProvider = getProvider();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDelete() {
		Uri uri = Uri.withAppendedPath(MyProvider.CONTENT_URI, "dummy");
		final int actual = mProvider.delete(uri, "_id = ?", new String[] { "1" });
		final int expected = 1;
		assertEquals(expected, actual);
	}
	
	public void testQuery() {
		Uri uri = Uri.withAppendedPath(MyProvider.CONTENT_URI, "dummy");
		final Cursor c = mProvider.query(uri, null, null, null, null);
		final int expected = 2;
		final int actual = c.getCount();
		assertEquals(expected, actual);
	}

	@Suppress
	public void testInsertUsingContentResolver() {
		final ContentValues values = new ContentValues();
		
		values.put("_id", 3);
		values.put("name", "Pluto");
		
		final Uri newUri = getMockContentResolver().insert(Uri.withAppendedPath(MyProvider.CONTENT_URI, "dummy"), values);
		assertNotNull(newUri);
	}
}
