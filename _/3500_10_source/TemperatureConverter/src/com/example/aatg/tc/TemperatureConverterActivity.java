/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;

import com.example.aatg.tc.TemperatureConverter.OP;

public class TemperatureConverterActivity extends Activity {

    public static final String TAG = "TemperatureConverterActivity";
    
    public static final int MENU_ID_PREFERENCES = 0;
    
	private static final boolean BENCHMARK_TEMPERATURE_CONVERSION = false;
	

	/**
	 * Changes fields values when text changes applying the corresponding method.
	 *
	 */
	public class TemperatureChangedWatcher implements TextWatcher {
		

		private final EditNumber mSource;
		private final EditNumber mDest;
		private OP mOp;

		/**
		 * @param mDest
		 * @param convert
		 * @throws NoSuchMethodException 
		 * @throws SecurityException 
		 */
		public TemperatureChangedWatcher(TemperatureConverter.OP op) {
			if ( op == OP.C2F ) {
				this.mSource = mCelsius;
				this.mDest = mFahrenheit;
			}
			else {
				this.mSource = mFahrenheit;
				this.mDest = mCelsius;
			}
			this.mOp = op;
		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
		 */
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
		 */
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
		 */
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (!mDest.hasWindowFocus() || mDest.hasFocus() || s == null ) {
				return;
			}
				
			final String str = s.toString();
			if ( "".equals(str) ) {
				mDest.setText("");
				return;
			}

            final long t0;
			if ( BENCHMARK_TEMPERATURE_CONVERSION ) {
				t0 = System.currentTimeMillis();
				Debug.startMethodTracing();
			}
			
			try {
				final double temp = Double.parseDouble(str);
				final double result = (mOp == OP.C2F) ? TemperatureConverter.celsiusToFahrenheit(temp) :
					TemperatureConverter.fahrenheitToCelsius(temp);
				final String resultString = String.format("%.2f", result);
				mDest.setNumber(result);
				mDest.setSelection(resultString.length());
			} catch (NumberFormatException e) {
				// WARNING
				// this is generated while a number is entered,
				// for example just a '-'
				// so we don't want to show the error
			} catch (InvalidTemperatureException e) {
				mSource.setError("ERROR: " + e.getLocalizedMessage());
			}
			
			if ( BENCHMARK_TEMPERATURE_CONVERSION ) {
				Debug.stopMethodTracing();
				long t = System.currentTimeMillis() - t0;
				Log.i(TAG, "TemperatureConversion took " + t + " ms to complete.");
			}
		}

	}

	private TemperatureConverterApplication mApplication;
	private EditNumber mCelsius;
	private EditNumber mFahrenheit;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	mApplication = (TemperatureConverterApplication) getApplication();
        mCelsius = (EditNumber) findViewById(R.id.celsius);
        mFahrenheit = (EditNumber) findViewById(R.id.fahrenheit);
        
        if ( savedInstanceState != null ) {
        	Log.d(TAG, "Should restore state from " + savedInstanceState);
        }
        
        mCelsius.addTextChangedListener(new TemperatureChangedWatcher(OP.C2F));
        mFahrenheit.addTextChangedListener(new TemperatureChangedWatcher(OP.F2C));
    }

	@Override
	protected void onResume() {
		super.onResume();

		final String format = "%." + mApplication.getDecimalPlaces() + "f";
		mCelsius.setFormat(format);
		mFahrenheit.setFormat(format);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Intent preferences = new Intent(this, TemperatureConverterPreferences.class);
		menu.add(Menu.NONE, MENU_ID_PREFERENCES, Menu.NONE, "Preferences").setIcon(android.R.drawable.ic_menu_preferences).setIntent(preferences);
		return super.onCreateOptionsMenu(menu);
	}


}