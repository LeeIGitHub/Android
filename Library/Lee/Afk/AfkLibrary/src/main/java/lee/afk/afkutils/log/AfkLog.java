package lee.afk.afkutils.log;

import java.util.Objects;

/**
 * 
 * @author Lee
 *	
 *	自定义Log
 *
 */
public class AfkLog {
	protected static String TAG="tag";
	protected static String DEVELOPER_NAME="developer name";
	protected static boolean IS_DEBUG = false;

	public static void p(String msg){
		android.util.Log.i(TAG,DEVELOPER_NAME + " "+ msg);
	}

	public static void p(String msg,Objects... obj){
		String message = String.format(msg,obj);
		p(message);
	}

	public static void e(String msg){
		android.util.Log.e(TAG,DEVELOPER_NAME+" " +msg);
	}

	public static void e(Exception e){
		e(e.getMessage());
		e.printStackTrace();
	}
}