/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockContext;

/**
 * @author diego
 *
 */
public class RenamingMockContext extends RenamingDelegatingContext {

	/**
	 * The renaming prefix.
	 */
	private static final String PREFIX = "test.";
	
	/**
	 * Constructor.
	 * @param context 
	 * 
	 * @param context
	 */
	public RenamingMockContext(Context context) {
		super(new DelegatedMockContext(context), PREFIX);
	}

	/**
	 * The DelegatedMockContext.
	 *
	 */
	private static class DelegatedMockContext extends MockContext {

		private Context mDelegatedContext;

		public DelegatedMockContext(Context context) {
			mDelegatedContext = context;
		}
		
		@Override
		public String getPackageName() {
			return mDelegatedContext.getPackageName();
		}
		
		@Override
		public SharedPreferences getSharedPreferences(String name, int mode) {
			return mDelegatedContext.getSharedPreferences(PREFIX + name, mode);
		}
	}
}
