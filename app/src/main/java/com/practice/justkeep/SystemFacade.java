
package com.practice.justkeep;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.UUID;

public class SystemFacade {


    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasHoneycombMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2;
    }

    public static boolean hasIcecreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasIcecreamSandwichMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean hasJellyBeanMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean Lollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static float getDensity(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static String getUserAgent(Context context) {
        if (hasJellyBeanMR1()) {
            return WebSettings.getDefaultUserAgent(context);
        } else {
            WebView view = new WebView(context);
            String userAgent = view.getSettings().getUserAgentString();
            view.destroy();
            return userAgent;
        }

    }

    public static int getScreenOrientation(Context context) {
        Configuration mConfiguration = context.getResources().getConfiguration(); //获取设置的配置信息
        return mConfiguration.orientation;
    }

    public static String getAppName(Context context) {
        return context.getApplicationInfo()
                .loadLabel(context.getPackageManager()).toString();
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static double getScreenInch(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        return diagonalInches;
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getAppVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getSimNum(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            return manager.getSimSerialNumber();
        }
        return null;
    }

    public static String getPhoneNum(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            return manager.getLine1Number();
        }
        return null;
    }

    public static String getMacAddr(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifi != null) {
            WifiInfo info = wifi.getConnectionInfo();
            if (info != null) {
                return info.getMacAddress();
            }
        }
        return null;
    }

    public static boolean hasPermission(Context context, String permission) {
        PackageManager manager = context.getPackageManager();
        return manager.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }



    public static boolean isOnInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static int getDeviceNetType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.getType();
            }
        }
        return -1;
    }

    public static int getDeviceSubNetType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.getSubtype();
            }
        }
        return -1;
    }

    public static String getOperatorID(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            return manager.getNetworkOperator();
        }
        return null;
    }

    public static int getCellularID(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            CellLocation cellLocation = manager.getCellLocation();
            if (GsmCellLocation.class.isInstance(cellLocation)) {
                return ((GsmCellLocation) cellLocation).getCid();
            } else if (CdmaCellLocation.class.isInstance(cellLocation)) {
                return ((CdmaCellLocation) cellLocation).getBaseStationId();
            }
        }
        return -1;
    }


    public static List<ScanResult> getWifiScanResult(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            if (wifiManager.isWifiEnabled()) {
                return wifiManager.getScanResults();
            }
        }
        return null;
    }

    public static WifiInfo getContectedWifiInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            if (wifiManager.isWifiEnabled()) {
                return wifiManager.getConnectionInfo();
            }
        }
        return null;
    }

    /**
     * 获取设备串号
     *
     * @return 设备串号
     */
    public static String getDeviceId(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            return telMgr.getDeviceId();
        } catch (SecurityException se) {

        }
        return "";

    }



    /**
     * 生成UUID标识，并写入文件
     *
     * @param fuuid 记录UUID标识的文件
     * @throws java.io.IOException
     */
    private static void writeUUID(File fuuid) throws IOException {
        FileOutputStream out = new FileOutputStream(fuuid);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }

    /**
     * 读取标识
     *
     * @param fuuid 记录UUID标识的文件
     * @return String
     * @throws java.io.IOException
     */
    private static String readUUID(File fuuid) throws IOException {
        RandomAccessFile f = new RandomAccessFile(fuuid, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }
}
