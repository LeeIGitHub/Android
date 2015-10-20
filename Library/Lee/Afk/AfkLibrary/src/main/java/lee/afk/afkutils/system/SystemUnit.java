package lee.afk.afkutils.system;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import lee.afk.afkutils.string.AfkString;

public class SystemUnit {

	/**
	 * 打开系统相册
	 *
	 * @param context
	 * @param requestCode
	 */
	public static void startSystemPhotoAlbum(Activity context, int requestCode) {
		startSystemPhotoAlbum(context, requestCode, false);
	}

	/**
	 * 打开系统相册,scale为true 可以截选图片
	 *
	 * @param context
	 * @param requestCode
	 * @param scale
	 */
	public static void startSystemPhotoAlbum(Activity context, int requestCode, boolean scale) {
		Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		if (scale) {
			intent.setType("image/*");
			intent.putExtra("crop", "true");
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 150);
			intent.putExtra("outputY", 150);
			intent.putExtra("scale", true);
			intent.putExtra("return-data", false);
			// intent.putExtra(MediaStore.EXTRA_OUTPUT, true);
		}

		context.startActivityForResult(intent, requestCode);
	}

	/**
	 * 打开系统相机
	 *
	 * @param context
	 * @param requestCode
	 */
	public static void startSystemCamera(Activity context, int requestCode) {
		// Intent intent = new Intent();
		// intent.setAction("android.media.action.IMAGE_CAPTURE");
		// intent.addCategory("android.intent.category.DEFAULT");
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		context.startActivityForResult(intent, requestCode);
	}

	/**
	 *
	 * 拨打电话
	 *
	 * @param context
	 * @param tel
	 */
	public static void callPhone(Context context, String tel) {
		callPhone(context, tel, Intent.ACTION_DIAL);
	}

	/**
	 *
	 * 拨打电话
	 *
	 * uses-permission android:name="android.permission.CALL_PHONE"
	 *
	 * @param context
	 * @param tel
	 * @param action
	 */
	public static void callPhone(Context context, String tel, String action) {
		Intent intent = new Intent(AfkString.isEmpty(action) ? Intent.ACTION_DIAL : action);
		Uri data = Uri.parse("tel:" + tel);
		intent.setData(data);
		context.startActivity(intent);
	}
}
