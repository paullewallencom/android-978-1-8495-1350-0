/**
 * 
 */
package com.example.aatg.tc.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.aatg.tc.InvalidTemperatureException;
import com.example.aatg.tc.TemperatureConverter;

/**
 * @author diego
 *
 */
public class TemperatureConverterTests {
	
	private static final HashMap<Double, Double> conversionTableDouble =
	    new HashMap<Double, Double>();
	
	static {
	    // initialize (c, f) pairs
	    conversionTableDouble.put(0.0, 32.0);
	    conversionTableDouble.put(100.0, 212.0);
	    conversionTableDouble.put(-1.0, 30.20);
	    conversionTableDouble.put(-100.0, -148.0);
	    conversionTableDouble.put(32.0, 89.60);
	    conversionTableDouble.put(-40.0, -40.0);
	    conversionTableDouble.put(-273.0, -459.40);
	}


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

	/**
	 * Test method for {@link com.example.aatg.tc.TemperatureConverter#fahrenheitToCelsius(double)}.
	 */
	@Test
	public final void testFahrenheitToCelsius() {
		for (double c: conversionTableDouble.keySet()) {
			final double f = conversionTableDouble.get(c);
			final double ca = TemperatureConverter.fahrenheitToCelsius(f);
			final double delta = Math.abs(ca - c);
			final String msg = "" + f + "F -> " + c + "C but is " + ca + 
                      " (delta " + delta + ")";
			assertTrue(msg, delta < 0.0001);
		}
	}

	/**
	 * Test method for {@link com.example.aatg.tc.TemperatureConverter#celsiusToFahrenheit(double)}.
	 */
	@Test
	public final void testCelsiusToFahrenheit() {
		for (double c: conversionTableDouble.keySet()) {
			final double f = conversionTableDouble.get(c);
			final double fa = TemperatureConverter.celsiusToFahrenheit(c);
			final double delta = Math.abs(fa - f);
			final String msg = "" + c + "C -> " + f + "F but is " + fa +
                      " (delta " + delta + ")";
			assertTrue(msg, delta < 0.0001);
		}
	}

	@Test
	public final void testExceptionForLessThanAbsoluteZeroF() {
		try {
			final double c = TemperatureConverter.fahrenheitToCelsius(
                      TemperatureConverter.ABSOLUTE_ZERO_F-1);
			fail("Less than absolute zero F not detected");
		}
		catch (InvalidTemperatureException ex) {
			// do nothing
		}
	}
	
	@Test
	public final void testExceptionForLessThanAbsoluteZeroC() {
		try {
			final double f = TemperatureConverter.celsiusToFahrenheit(
                      TemperatureConverter.ABSOLUTE_ZERO_C-1);
			fail("Less than absolute zero C not detected");
		}
		catch (RuntimeException ex) {
			// do nothing
		}
	}

}
