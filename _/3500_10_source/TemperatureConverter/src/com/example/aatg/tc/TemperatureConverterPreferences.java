package com.example.aatg.tc;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class TemperatureConverterPreferences extends PreferenceActivity {

	public TemperatureConverterPreferences() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);
	}

}
