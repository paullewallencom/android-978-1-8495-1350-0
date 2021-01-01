/**
 * 
 */
package com.example.aatg.tc.test;


import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Application;

import com.example.aatg.tc.TemperatureConverterApplication;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * @author diego
 *
 */
@RunWith(RobolectricTestRunner.class)
public class TemperatureConverterActivityUnitTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testApplication() {
		Application application = new TemperatureConverterApplication();
		assertNotNull(application);
	}
}
