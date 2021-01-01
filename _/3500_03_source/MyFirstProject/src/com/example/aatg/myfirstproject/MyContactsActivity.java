/**
 * Copyright 2010-2011 (C) Diego Torres Milano
 */
package com.example.aatg.myfirstproject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

/**
 * This Activity requires some permissions just to be verified by tests:
 * <ul>
 * <li><code>android.permission.WRITE_EXTERNAL_STORAGE</code>
 * <li><code>android.permission.READ_CONTACTS</code>
 * <li><code>android.permission.WRITE_CONTACTS</code>
 * </ul>
 * 
 * @author diego
 *
 */
public class MyContactsActivity extends Activity {
	
	private static final boolean WRITE_EXTERNAL_FILE = true;
	private static final boolean START_CALL_ACTIVITY = true;


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("My Contacts Activity");
        setContentView(tv);
        if ( WRITE_EXTERNAL_FILE ) {
        	writeExternalFile();
        }
        if ( START_CALL_ACTIVITY ) {
        	Intent intent = new Intent();
        	intent.setAction(Intent.ACTION_CALL);
        	intent.setData(Uri.parse("tel:555-5555"));
        	startActivity(intent);
        }
    }
        

    private void writeExternalFile() {
        final File dir = Environment.getExternalStorageDirectory();
        final File file = new File(dir, "dummy");
        android.util.Log.d("writeExternalFile", "file=" + file);
        final BufferedOutputStream os;
        
		try {
			os = new BufferedOutputStream(new FileOutputStream(file));
			os.write("This is a sample".getBytes());
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			SecurityException ex = new SecurityException("Should have detected that android.permission.WRITE_EXTERNAL_STORAGE is required.");
			String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
			android.util.Log.w("writeExternalFile", "contains " + permission + "? :" + ex.getMessage().contains(permission));
			throw new SecurityException("Should have detected that android.permission.WRITE_EXTERNAL_STORAGE is required.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SecurityException("Should have detected that android.permission.WRITE_EXTERNAL_STORAGE is required.");
		}

    }
}
