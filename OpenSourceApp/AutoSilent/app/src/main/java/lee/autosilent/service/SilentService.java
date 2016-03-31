package lee.autosilent.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lee.afk.afknet.AfkWifiTools;
import lee.afk.afkutils.log.LeeLog;
import lee.autosilent.bean.Task;
import lee.autosilent.utils.AudioUtils;
import lee.autosilent.utils.MyFinalDbUtils;

/**
 * Created by Lee on 2015/10/21.
 */
public class SilentService extends Service {
    private AudioUtils mAudioutils;
    private Task mTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        if(Build.VERSION.SDK_INT < 18)
//        startForeground(-1213,new Notification());
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mBroadcastReceiver, filter);

        mAudioutils = new AudioUtils(this);

//        new Timer().schedule(mTimerTask,0,1000*2);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        LeeLog.p("Service  onStartCommand");
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
//        LeeLog.p("Service  onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
//        LeeLog.p("Service  onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
//        LeeLog.p("Service  destroy");
        unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
//        LeeLog.p("Service  onLowMemory");
        super.onLowMemory();
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                networkHasChanged();
            }
        }
    };

    private void networkHasChanged() {
//        LeeLog.p("Service  networkHasChanged");
        getTaskList();

        if (mTask != null) {
//            LeeLog.p("Service  changeRingerMode");
            changeRingerMode(mTask.getMode());//如果找到存在列表里的task,则设置为相应的静音模式
        } else {
//            LeeLog.p("Service  backToYourRingerMode");
            mAudioutils.backToYourRingerMode();//如果没找到，则还原成本来的静音模式
        }
    }


    private void getTaskList() {

        WifiInfo wifiInfo = AfkWifiTools.getWifiInfo(this);

        if (wifiInfo == null) {
            mTask = null;
            return;
        }

        mTask = MyFinalDbUtils.getTaskByMacAddress(this, wifiInfo.getBSSID());

        String msg = MessageFormat.format("当前网络：{0}，{1}", wifiInfo == null ? "null" : wifiInfo.getSSID(),mTask == null? "无匹配网络":"已匹配网络："+mTask.getSsid());
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    private void changeRingerMode(int mode) {
        mAudioutils.setRingerMode(mode);
    }


    public class MyBinder extends Binder {
        public SilentService getService() {
            return SilentService.this;
        }
    }
}
