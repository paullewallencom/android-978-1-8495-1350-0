package com.example.aatg.tc.test.launchperf;

import android.app.Activity;
import android.os.Bundle;

/**
 * Instrumentation class for {@link TemperatureConverterActivity} launch performance testing.
 */
public class TemperatureConverterActivityLaunchPerformance extends
		LaunchPerformanceBase {
    /**
     * Constructor.
     */
    public TemperatureConverterActivityLaunchPerformance() {
        super();
    }

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        mIntent.setClassName("com.example.aatg.tc",
                 "com.example.aatg.tc.TemperatureConverterActivity");
        start();
    }

    /**
     * Calls LaunchApp and finish.
     */
    @Override
    public void onStart() {
        super.onStart();
        LaunchApp();
        finish(Activity.RESULT_OK, mResults);
    }
}
