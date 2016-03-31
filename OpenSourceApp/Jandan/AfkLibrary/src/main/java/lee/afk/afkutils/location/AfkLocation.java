package lee.afk.afkutils.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import lee.afk.afkutils.log.LeeLog;

/**
 * Created by Lee on 2015/9/6.
 */
public class AfkLocation {

    private static AfkLocation mAfkLocation;

    /**
     * 地理位置更新距离
     */
    private int LOCATION_UPDATE_DISTANCE = 0;

    /**
     * 地理位置更新时间
     */
    private int LOCATION_UPDATE_TIME = 1 * 1000;

    private Context mContext;

    /**
     * 地理位置监听器
     */
    private OnLocationChangeListener mOnLocationChangeListener;

    private LocationListener mLocationListener;

    /**
     * 当前地理位置
     */
    private Location mLocation;

    private LocationManager mLocationManager;


    public interface OnLocationChangeListener {
        void onChange(Location location);
    }


    /**
     * 初始化AfkLocation
     */
    public static AfkLocation init(Context context, OnLocationChangeListener listener) {
        if (mAfkLocation == null)
            mAfkLocation = new AfkLocation(context, listener);
        return mAfkLocation;
    }

    /**
     * 销毁AfkLocation
     */
    public static void destroy() {
        if (mAfkLocation == null)
            return;
        mAfkLocation.stop();
    }

    private AfkLocation(Context context, OnLocationChangeListener listener) {
        this.mContext = context;
        this.mOnLocationChangeListener = listener;
        initLocation();
    }

    private void initLocation() {
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LeeLog.p("mLocationListener   onLocationChanged   " + location.toString());
//                mLocation = location;
//                if (mOnLocationChangeListener != null) {
//                    mOnLocationChangeListener.onChange(location);
//                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                LeeLog.p("mLocationListener   onStatusChanged   " + provider + "   " + status);
            }

            @Override
            public void onProviderEnabled(String provider) {
                LeeLog.p("mLocationListener   onProviderEnabled   " + provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                LeeLog.p("mLocationListener   onProviderDisabled   " + provider);
            }
        };

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        String provider = mLocationManager.getBestProvider(criteria,true);
        String provider = LocationManager.PASSIVE_PROVIDER;
        mLocationManager.requestLocationUpdates(provider, LOCATION_UPDATE_DISTANCE, LOCATION_UPDATE_TIME, mLocationListener);
        mLocation = mLocationManager.getLastKnownLocation(provider);
    }

    private void stop() {
        mLocationManager.removeUpdates(mLocationListener);
    }

    /**
     * 设置地理位置监听器
     *
     * @param listener
     */
    public void setOnLocationChangeListener(OnLocationChangeListener listener) {
        this.mOnLocationChangeListener = listener;
    }

    /**
     * 获取当前地理位置
     *
     * @return
     */
    public Location getLocation() {
        return mLocation;
    }


}
