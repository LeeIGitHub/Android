package lee.autosilent.utils;

import android.content.Context;

import net.tsz.afinal.FinalDb;

import java.util.List;

import lee.autosilent.bean.Task;

/**
 * Created by dahan on 2015/10/22.
 */
public class MyFinalDbUtils {
    private static FinalDb finalDb;

    private static MyFinalDbUtils mInstance;

    private static MyFinalDbUtils create(Context context){
        if(mInstance == null){
            mInstance = new MyFinalDbUtils(context);
        }
        return mInstance;
    }

    private static void init(Context context){
        create(context);
    }

    public MyFinalDbUtils(Context context) {
        finalDb = FinalDb.create(context);
    }

    public static void saveTask(Context context,Task task){
        init(context);
        finalDb.save(task);
    }

    public static void deleteTask(Context context,Task task){
        init(context);
        finalDb.delete(task);
    }

    public static List<Task> getAllTaskList(Context context){
        init(context);
        return finalDb.findAll(Task.class,"line");
//        return finalDb.findAll(Task.class);
    }

    public static Task getTaskByLineNum(Context context,int line){
        init(context);
        List<Task> taskList = finalDb.findAllByWhere(Task.class,"line = " + line);
        if(taskList == null)
            return null;

        if(taskList.size() <= 0)
            return null;
        return taskList.get(0);
    }

    /**
     * 根据Mac地址寻找已存储的Task(如果有多个，只返回第一个）
     * @param macAddress
     * @return
     */
    public static Task getTaskByMacAddress(Context context,String macAddress){
        init(context);

        List<Task> taskList = finalDb.findAllByWhere(Task.class,"mac = '" + macAddress + "'");
        if(taskList == null)
            return null;

        if(taskList.size() <= 0)
            return  null;

        return taskList.get(0);
    }

    /**
     * 整理task 的行号
     * @param context
     */
    public static void resetTaskListLineNumber(Context context){
        init(context);

        List<Task> taskList = getAllTaskList(context);

        if(taskList == null)
            return ;

        for (int i = 0; i < taskList.size(); i ++){
            Task task = taskList.get(i);
            task.setLine(i+1);
            finalDb.update(task,"id = " + task.getId());
        }
    }
}
