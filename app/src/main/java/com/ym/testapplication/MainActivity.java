package com.ym.testapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

import static com.ym.testapplication.SystemUtil.getUserInfo;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: " + SystemUtil.getUniquePsuedoID());
        SystemUtil.showSystemParameter(this);
        Log.e(TAG, "onCreate: " + getUserInfo() );
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        Log.e(TAG, "onCreate: versionCode: " + versionCode + " versionName: " + versionName );

        Integer test = null;
        try{
        test.toString();
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        HashMap map = new HashMap();
        map.put("name", "value");
        map.put("name", null);
        Log.e(TAG, "onCreate: " + String.valueOf(map.get("name")));

    }


}
