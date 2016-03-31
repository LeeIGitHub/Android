package lee.autosilent.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.widget.Toast;

import java.text.MessageFormat;

import lee.afk.afknet.AfkWifiTools;
import lee.autosilent.bean.Task;
import lee.autosilent.utils.AudioUtils;
import lee.autosilent.utils.MyFinalDbUtils;

/**
 * Created by lee on 2016/1/29.
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {
    private Context mContext;
    private AudioUtils mAudioutils;
    private Task mTask;

    @Override
    public void onReceive(Context context, Intent intent) {
        /*
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        Toast.makeText(context,"网络change 啦",Toast.LENGTH_SHORT).show();
        */

        mContext = context;

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            networkHasChanged();
        }
    }

    private void networkHasChanged() {
        if(mAudioutils == null){
            mAudioutils = new AudioUtils(mContext);
        }

        getTaskList();

        if (mTask != null) {
            changeRingerMode(mTask.getMode());//如果找到存在列表里的task,则设置为相应的静音模式
        } else {
            mAudioutils.backToYourRingerMode();//如果没找到，则还原成本来的静音模式
        }
    }

    private void getTaskList() {
        WifiInfo wifiInfo = AfkWifiTools.getWifiInfo(mContext);
        if (wifiInfo == null) {
            mTask = null;
            return;
        }

        mTask = MyFinalDbUtils.getTaskByMacAddress(mContext, wifiInfo.getBSSID());
        String msg = MessageFormat.format("当前网络：{0}，{1}", wifiInfo == null ? "null" : wifiInfo.getSSID(), mTask == null ? "无匹配网络" : "已匹配网络：" + mTask.getSsid());
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }


    private void changeRingerMode(int mode) {
        mAudioutils.setRingerMode(mode);
    }

}
