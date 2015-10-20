package lee.afk.afkutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lee on 2015/7/29.
 */
public class SharedPreferenceUtils {

    private static final String SPFILE_NAME = "SharedPreference";

    public static void putValue(Context context,String key, Object value){
        putValue(getSharedPreferences(context,SPFILE_NAME).edit(),key,value);
    }

    protected static void putValue(SharedPreferences.Editor editor,String key, Object value){
        String type = value.getClass().getSimpleName();
        if("String".equals(type))
            editor.putString(key,(String)value);
        else if("Integer".equals(type))
            editor.putInt(key, (Integer) value);
        else if("Boolean".equals(type))
            editor.putBoolean(key, (Boolean) value);
        else if("Float".equals(type))
            editor.putFloat(key, (Float) value);
        else if("Long".equals(type))
            editor.putLong(key, (Long) value);

        editor.commit();
    }

    public static SharedPreferences getSharedPreferences(Context context,String name){
        return context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }
}
