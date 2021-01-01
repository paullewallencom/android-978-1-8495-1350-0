/**
 * Copyright 2010-2011 (C) Diego Torres Milano
 */
package com.example.aatg.myfirstproject;

import com.example.libdummy.Dummy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MyFirstProjectActivity extends Activity {
    private EditText mMessage;
	private Button mCapitalize;
	private Dummy mDummy;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mMessage = (EditText) findViewById(R.id.message);
        mCapitalize = (Button) findViewById(R.id.capitalize);
        
        mCapitalize.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				mMessage.setText(mMessage.getText().toString().toUpperCase());
			}
        });
        
        mDummy = new Dummy();
    }

	public static void methodThatShouldThrowException() throws Exception {
		throw new Exception("This is an exception");
	}

	public Dummy getDummy() {
		return mDummy;
	}
	
}