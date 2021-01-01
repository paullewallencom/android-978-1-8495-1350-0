/**
 * 
 */
package com.example.aatg.tc.test;

import com.example.aatg.tc.TemperatureConverterActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

/**
 * @author diego
 *
 */
public class TemperatureConverterActivityUnitTest extends
		ActivityUnitTestCase<TemperatureConverterActivity> {

	private Intent mStartIntent;

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
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityUnitTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPreconditions() {
		startActivity(mStartIntent, null, null);
		assertNotNull(getActivity());
	}
	
    public void testLifeCycleCreate() {
        TemperatureConverterActivity activity = startActivity(mStartIntent, null, null);
        
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
	
}
