package lee.afk.afkutils.string;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class AfkString {
	/**
	 *
	 * 判断String是否为空
	 *
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		if (string == null || string.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * 获取图片地址
	 *
	 * @param context
	 * @param startActivityRequestCode
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 * @return
	 */
	public static String getImagePath(Context context, int startActivityRequestCode, int requestCode, int resultCode,
									  Intent data) {
		if (startActivityRequestCode == requestCode) {
			if (resultCode == Activity.RESULT_OK) {
				return getImagePath(context, data.getData());
//				Uri uri = data.getData();
//				String[] filePathColums = { MediaStore.Images.Media.DATA };
//				Cursor cursor = context.getContentResolver().query(uri, filePathColums, null, null, null);
//				cursor.moveToFirst();
//				int columnIndex = cursor.getColumnIndex(filePathColums[0]);
//				String path = cursor.getString(columnIndex);
//				cursor.close();
//				return path;
			}
			else {
				return "";
			}
		}
		return "";
	}

	/**
	 *
	 * 获取图片地址
	 *
	 * @param context
	 * @param uri
	 * @return
	 */
	public static String getImagePath(Context context,Uri uri){
		String[] filePathColums = { MediaStore.Images.Media.DATA };
		Cursor cursor = context.getContentResolver().query(uri, filePathColums, null, null, null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePathColums[0]);
		String path = cursor.getString(columnIndex);
		cursor.close();
		return path;
	}
	
}
