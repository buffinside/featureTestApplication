package com.ym.testapplication;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.UUID;

import static com.ym.testapplication.SystemUtil.getDeviceDetail;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: " + SystemUtil.getUniquePsuedoID());
        SystemUtil.showSystemParameter(this);
        Log.e(TAG, "onCreate: " + getDeviceDetail() );
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
    }


}
