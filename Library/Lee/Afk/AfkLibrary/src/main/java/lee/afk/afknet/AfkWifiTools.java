package lee.afk.afknet;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

/**
 * Created by Lee on 2015/10/19.
 */
public class AfkWifiTools {
    private static WifiManager getWifiManager(Context context){
        return (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
    }

    /**
     * 获取当前wifiinfo
     * @param context
     * @return
     */
    public static WifiInfo getWifiInfo(Context context){
        WifiManager wifiManager = getWifiManager(context);

        int wifiState = wifiManager.getWifiState();

        if(wifiState != WifiManager.WIFI_STATE_ENABLED && wifiState != WifiManager.WIFI_STATE_ENABLING)
            return null;

        return wifiManager.getConnectionInfo();
    }

    /**
     * 获取当前wifi 的 SSID
     * @param context
     * @return
     */
    public static String getWifiNowSSID(Context context){
        WifiInfo wifiInfo = getWifiInfo(context);

        if(wifiInfo == null)
            return "";

        return wifiInfo.getSSID();
    }

    /**
     * 获取wifi的Mac地址
     * @param context
     * @return
     */
    public static String getWifiMacAddress(Context context){
        WifiInfo wifiInfo = getWifiInfo(context);

        if(wifiInfo == null)
            return "";

        return wifiInfo.getMacAddress();
    }

    /**
     * 进入系统的wifi设置界面
     * @param context
     */
    public static void openSystemWifiSetting(Context context){
        Intent intent =new Intent(Settings.ACTION_WIFI_SETTINGS);
        context.startActivity(intent);
    }
}
