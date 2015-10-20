package lee.afk.afkutils;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by Lee 2015/10/19.
 */
public class AfkAudioTools {
    public static AudioManager getAudioManager(Context context){
        return (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * 设置铃音为静音
     * @param context
     */
    public static void setRingerSilent(Context context){
        AudioManager manager = getAudioManager(context);

        if(manager == null)
            return ;

        manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    /**
     * 设置铃音为震动
     * @param context
     */
    public static void setRingerVibrate(Context context){
        AudioManager manager = getAudioManager(context);

        if(manager == null)
            return ;

        manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }

    /**
     * 设置铃音为正常
     * @param context
     */
    public static void setRingerNormal(Context context){
        AudioManager manager = getAudioManager(context);

        if(manager == null)
            return ;

        manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    /**
     * 获取铃音 Mode
     * @param context
     * @return
     */
    public static int getRingerMode(Context context){
        AudioManager manager = getAudioManager(context);

        if(manager == null)
            return AudioManager.RINGER_MODE_NORMAL;

        return manager.getRingerMode();
    }

    /**
     * 设置铃音 Mode
     * @param context
     * @param mode
     */
    public static void setRingerMode(Context context,int mode){
        AudioManager manager = getAudioManager(context);

        if(manager == null)
            return ;

        manager.setRingerMode(mode);
    }
}
