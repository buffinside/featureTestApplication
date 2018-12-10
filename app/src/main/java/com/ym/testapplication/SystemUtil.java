package com.ym.testapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Locale;
import java.util.UUID;

/**
 * 系统工具类
 * Created by zhuwentao on 2016-07-18.
 * https://blog.csdn.net/zhuwentao2150/article/details/51946387
 */
public class SystemUtil {

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
//    @SuppressLint("MissingPermission") //正常来说应该要检查请求权限是否被用户拒绝了，但是联创的sdk貌似不支持api为23。只好忍痛了。
//    public static String getIMEI(Context ctx) {
//        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
//        if (tm != null) {
//        //    if (ctx.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    Activity#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for Activity#requestPermissions for more details.
//          //      return TODO;
//            //}
//            return tm.getDeviceId();
//        }
//        return null;
//    }

    public static void showSystemParameter(Activity activity) {
        String TAG = "mainactivity";
        Log.e(TAG, "系统参数：");
        Log.e(TAG, "手机厂商：" + SystemUtil.getDeviceBrand());
        Log.e(TAG, "手机型号：" + SystemUtil.getSystemModel());
        Log.e(TAG, "手机当前系统语言：" + SystemUtil.getSystemLanguage());
        Log.e(TAG, "Android系统版本号：" + SystemUtil.getSystemVersion());
    //    Log.e(TAG, "手机IMEI：" + SystemUtil.getIMEI(activity.getApplicationContext()));
    }

    /**
     * https://stackoverflow.com/questions/2785485/is-there-a-unique-android-device-id
     * Return pseudo unique ID 生成唯一设备码（注意会存在冲突的情况）
     * @return ID
     */
    public static String getUniquePsuedoID() {
        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their device or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Thanks http://www.pocketmagic.net/?p=1662!
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        // Thanks to @Roman SL!
        // https://stackoverflow.com/a/4789483/950427
        // Only devices with API >= 9 have android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // If a user upgrades software or roots their device, there will be a duplicate entry
        String serial = null;
        try {
            serial = String.valueOf(android.os.Build.class.getField("SERIAL").get(null));

            // Go ahead and return the serial for api => 9
            return String.valueOf(new UUID(m_szDevIDShort.hashCode(), serial.hashCode()));
        } catch (Exception exception) {
            // String needs to be initialized
            serial = "serial"; // some value
        }

        // Thanks @Joe!
        // https://stackoverflow.com/a/2853253/950427
        // Finally, combine the values we have found by using the UUID class to create a unique identifier
        return String.valueOf(new UUID(m_szDevIDShort.hashCode(), serial.hashCode()));
    }

    /**
     * https://stackoverflow.com/questions/20697008/how-to-get-device-aosp-build-number-in-android-devices-programmatically
     * @return json格式的字符串表示的设备详细信息
     */
    public static String getUserInfo(){
        String mString = "";

        mString = mString.concat("\n{")
                        .concat("\"user_id\" : \"" + getUniquePsuedoID() + "\",")
                        .concat("\n\"device_brand\" : \"" + Build.BRAND + "\",")
                        .concat("\n\"device_model\" : \"" + Build.MODEL + "\",")
                        .concat("\n\"build_number\" : \"" + Build.ID + "\",")
                        .concat("\n\"version_release\" : \"" + Build.VERSION.RELEASE + "\",")
                        .concat("\n\"version_sdk\" : \"" + Build.VERSION.SDK + "\",")
                        .concat("\n\"version_incremental\" : \"" + Build.VERSION.INCREMENTAL + "\",")
                        .concat("\n\"system_language\" : \"" + getSystemLanguage() + "\"")
                        .concat("}");
        return String.valueOf(mString);
    }
}

