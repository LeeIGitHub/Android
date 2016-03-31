package lee.autosilent.database;

import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;

import java.util.List;

import lee.afk.afknet.AfkWifiTools;
import lee.autosilent.bean.Task;
import lee.autosilent.constants.AppConstants;
import lee.autosilent.utils.AudioUtils;
import lee.autosilent.utils.MyFinalDbUtils;

/**
 * Created by Lee on 2015/11/10.
 */
public class TaskDataBase {
    private Context mContext;

    public TaskDataBase(Context mContext) {
        this.mContext = mContext;
    }

    public Task saveTask(int mode,AudioUtils audioUtils){
        int ringerMode = audioUtils.getRingerMode();
        int lineNum = 1;
        switch (mode){
            case AppConstants.RINGER_MODE_SILENT:
                ringerMode = AudioManager.RINGER_MODE_SILENT;
                break;
            case AppConstants.RINGER_MODE_VIBRATE:
                ringerMode = AudioManager.RINGER_MODE_VIBRATE;
                break;
            case AppConstants.RINGER_MODE_NORMAL:
                ringerMode = AudioManager.RINGER_MODE_NORMAL;
                break;
        }

        WifiInfo wifiInfo = AfkWifiTools.getWifiInfo(mContext);

        if(wifiInfo == null)
            return null;

        List<Task> taskList = getAllTask();
        if(taskList != null){
            if(taskList.size() >  0){
                lineNum = taskList.get(taskList.size()-1).getLine() + 1;
            }
        }

        Task task = new Task();
        task.setMac(wifiInfo.getBSSID());
        task.setMode(ringerMode);
        task.setSsid(wifiInfo.getSSID());
        task.setLine(lineNum);

        MyFinalDbUtils.saveTask(mContext, task);

        return task;
    }

    public void saveTask(Task task){
        MyFinalDbUtils.saveTask(mContext,task);
    }

    public Task deleteTask(int line){

        List<Task> taskList = MyFinalDbUtils.getAllTaskList(mContext);

        Task task = MyFinalDbUtils.getTaskByLineNum(mContext,line);

        MyFinalDbUtils.deleteTask(mContext, task);

        return task;
    }

    public List<Task> getAllTask(){
        return MyFinalDbUtils.getAllTaskList(mContext);
    }
}