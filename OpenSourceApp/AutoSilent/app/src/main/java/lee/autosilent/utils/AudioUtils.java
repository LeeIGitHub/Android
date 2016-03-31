package lee.autosilent.utils;

import android.content.Context;

import lee.afk.afkutils.AfkAudioTools;

/**
 * Created by Lee on 2015/10/22.
 */
public class AudioUtils{
    private Context context;

    public AudioUtils(Context context) {
        this.context = context;
    }

    /**
     * 还原用户的原来的 铃音模式（现在默认为有声音,就是Normal模式）
     */
    public void backToYourRingerMode(){
        AfkAudioTools.setRingerNormal(context);
    }

    /**
     * 设置铃音模式
     * @param mode
     */
    public void setRingerMode(int mode){
        AfkAudioTools.setRingerMode(context, mode);
    }

    /**
     * 获取现在的RingerMode
     */
    public int getRingerMode(){
        return AfkAudioTools.getRingerMode(context);
    }
}
