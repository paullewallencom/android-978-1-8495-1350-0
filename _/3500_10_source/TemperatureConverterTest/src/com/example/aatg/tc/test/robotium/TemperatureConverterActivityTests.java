/**
 * 
 */
package com.example.aatg.tc.test.robotium;

import android.test.ActivityInstrumentationTestCase2;

import com.example.aatg.tc.TemperatureConverter;
import com.example.aatg.tc.TemperatureConverterActivity;
import com.jayway.android.robotium.solo.Solo;

/**
 * @author diego
 *
 */
public class TemperatureConverterActivityTests extends
		ActivityInstrumentationTestCase2<TemperatureConverterActivity> {
	
	private static final int CELSIUS = 0;
	private static final int FAHRENHEIT = 1;
	private static final int DECIMAL_PLACES = 2;
	
	private Solo mSolo;
	private TemperatureConverterActivity mActivity;
	/**
	 * @param name
	 */
	public TemperatureConverterActivityTests(String name) {
		super(TemperatureConverterActivity.class);
		setName(name);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		mActivity = getActivity();
		mSolo = new Solo(getInstrumentation(), mActivity);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	protected void tearDown() throws Exception {
		try {
			mSolo.finalize();
		}
		catch (Throwable ex) {
			ex.printStackTrace();
		}
		
		mActivity.finish();
		
		super.tearDown();
	}

	public final void testFahrenheitToCelsiusConversion() {
		mSolo.clearEditText(CELSIUS);
		mSolo.clearEditText(FAHRENHEIT);
		
		final double f = 32.5d;
		mSolo.clickOnEditText(FAHRENHEIT);
		mSolo.enterText(FAHRENHEIT, Double.toString(f));
		mSolo.clickOnEditText(CELSIUS);
		final double expectedC = TemperatureConverter.fahrenheitToCelsius(f);
		final double actualC = Double.parseDouble(mSolo.getEditText(CELSIUS).getText().toString());
		final double delta = Math.abs(expectedC - actualC);
		final String msg = "" + f + "F -> " + expectedC + "C but was " + actualC + "C (delta " + delta + ")";
		assertTrue(msg, delta < 0.005);
	}
	
	public final void testOnCreateOptionsMenu() {
		final int decimalPlaces = 5;
		final String numberRE = "^[0-9]+$";
		
		mSolo.sendKey(Solo.MENU);
		mSolo.clickOnText("Preferences");
		mSolo.clickOnText("Decimal places");
		assertTrue(mSolo.searchText(numberRE));
		mSolo.clearEditText(DECIMAL_PLACES);
		assertFalse(mSolo.searchText(numberRE));
		mSolo.enterText(DECIMAL_PLACES,
                Integer.toString(decimalPlaces));
		mSolo.clickOnButton("OK");
		mSolo.goBack();
		
		mSolo.sendKey(Solo.MENU);
		mSolo.clickOnText("Preferences");
		mSolo.clickOnText("Decimal places");
		assertTrue(mSolo.searchText(numberRE));
		assertEquals(decimalPlaces,
                 Integer.parseInt(mSolo.getEditText(DECIMAL_PLACES)
                     .getText().toString()));
	}
}
