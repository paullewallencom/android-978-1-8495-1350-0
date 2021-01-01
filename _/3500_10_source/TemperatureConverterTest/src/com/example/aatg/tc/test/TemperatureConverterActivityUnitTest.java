/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc.test;

import com.example.aatg.tc.TemperatureConverterActivity;
import com.example.aatg.tc.TemperatureConverterApplication;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;

/**
 * @author diego
 *
 */
public class TemperatureConverterActivityUnitTest extends
		ActivityUnitTestCase<TemperatureConverterActivity> {

	private Intent mStartIntent;
	private Instrumentation mInstrumentation;

	public TemperatureConverterActivityUnitTest() {
		this("TemperatureConverterActivityUnitTest");
	}
	
	
	/**
	 * @param name
	 */
	public TemperatureConverterActivityUnitTest(String name) {
		super(TemperatureConverterActivity.class);
		setName(name);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityUnitTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		mStartIntent = new Intent(Intent.ACTION_MAIN);
		mInstrumentation = getInstrumentation();
		setApplication(new TemperatureConverterApplication());
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityUnitTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPreconditions() {
		assertNotNull(mInstrumentation);
	}
	
    public void testLifeCycleCreate() {
        final TemperatureConverterActivity activity = startActivity(mStartIntent, null, null);
        
        // At this point, onCreate() has been called, but nothing else
        // Complete the startup of the activity
        getInstrumentation().callActivityOnStart(activity);
        getInstrumentation().callActivityOnResume(activity);
        
        // At this point you could test for various configuration aspects, or you could
        // use a Mock Context to confirm that your activity has made certain calls to the system
        // and set itself up properly.
        
        getInstrumentation().callActivityOnPause(activity);
        
        // At this point you could confirm that the activity has paused properly, as if it is
        // no longer the topmost activity on screen.
        
        getInstrumentation().callActivityOnStop(activity);
        
        // At this point, you could confirm that the activity has shut itself down appropriately,
        // or you could use a Mock Context to confirm that your activity has released any system
        // resources it should no longer be holding.

        // ActivityUnitTestCase.tearDown(), which is always automatically called, will take care
        // of calling onDestroy().
    }
	
	public final void testOnCreateBundle() {
		final Bundle savedInstanceState = new Bundle();
		savedInstanceState.putString("dummy", "dummy");
		final Intent intent = new Intent(mInstrumentation.getTargetContext(),
                 TemperatureConverterActivity.class);
		startActivity(intent, savedInstanceState, null);
		TemperatureConverterActivity activity = getActivity();
		assertNotNull(activity);
	}
}
