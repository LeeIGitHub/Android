package lee.afk.afkutils.bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import lee.afk.afkutils.string.AfkString;
import lee.afk.afkutils.log.LeeLog;

public class AfkBitmapUtil {

	/**
	 * 
	 * 从图片的文件地址获取bitmap对象
	 * 
	 * @param pathName
	 * @return
	 */
	public static Bitmap getBitmap(String pathName) {
		return getBitmap(pathName, -1);
	}

	/**
	 * 
	 * 从图片的文件地址获取bitmap对象
	 * 
	 * @param pathName
	 * @param inSampleSize
	 *            (缩小倍数 )
	 * @return
	 */
	public static Bitmap getBitmap(String pathName, int inSampleSize) {
		if (inSampleSize > 0) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPurgeable = true;
			options.inSampleSize = inSampleSize;
			return BitmapFactory.decodeFile(pathName, options);
		}
		return BitmapFactory.decodeFile(pathName);
	}

	/**
	 * 
	 * 从onActivityResult里获取bitmap(拍照时)
	 * 
	 * @param context
	 * @param startActivityRequestCode
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 * @return
	 */
	public static Bitmap getBitmapFromResult(Context context, int startActivityRequestCode, int requestCode,
			int resultCode, Intent data) {
		Bitmap image = null;
		if (startActivityRequestCode == requestCode) {
			if (resultCode == Activity.RESULT_OK) {
				if (data != null) {
					if (data.hasExtra("data")) {
						Uri uri = data.getData();
						if (uri == null) {
							Bundle bundle = data.getExtras();
							if (bundle != null) {
								image = (Bitmap) bundle.get("data");
							}
						}
						else {
						}
					}
				}
			}
		}
		return image;
	}

	/**
	 * 获取bitmap 从onActivityResult 打开相册里获取
	 * @param context
	 * @param startActivityRequestCode
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 * @return
	 */
	public static Bitmap getBitmapFromResultByAlbum(Context context, int startActivityRequestCode, int requestCode,
			int resultCode, Intent data) {
		Bitmap image = null;

		if (startActivityRequestCode == requestCode) {
			if (resultCode == Activity.RESULT_OK) {
				Uri uri = data.getData();
				if (uri != null) {
					String path = AfkString.getImagePath(context, uri);
					image = getBitmap(path);
				}
				else {
					Bundle bundle = data.getExtras();
					if (bundle != null) {
						image = bundle.getParcelable("data");
					}
				}
			}
		}
		return image;
	}
	
	/**
	 * 
	 * 将bitmap转化为base64字节流
	 * 
	 * @param bitmap
	 * @return
	 */
	public static String bitmapToBase64(Bitmap bitmap){
		if(bitmap == null)
			return "";
		
		String base64 = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try{
			byteArrayOutputStream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
			
			byteArrayOutputStream.flush();
			byteArrayOutputStream.close();
			
			byte[] bitmapBytes = byteArrayOutputStream.toByteArray();
			base64 = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			
		}
		catch(IOException e){
			LeeLog.e(e);
		}
		finally {
			try{
				if(byteArrayOutputStream != null){
					byteArrayOutputStream.flush();
					byteArrayOutputStream.close();
				}
			}
			catch(IOException e){
				LeeLog.e(e);
			}
		}
		return base64;
	}

	/**
	 * 将Bitmap缩放到指定大小
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap setBitmapSize(Bitmap bitmap, int w,int h){
		Bitmap BitmapOrg = bitmap;
		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		float scaleWidth=((float)newWidth)/width;
		float scaleHeight=((float)newHeight)/height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newBitmap = Bitmap.createBitmap(BitmapOrg,0,0,width,height,matrix,true);
		return newBitmap;
	}

	/**
	 * 将Drawable转化为Bitmap
	 *
	 * @param drawable
	 * @return
	 */
	public static Bitmap getBitmapFromDrawable(Drawable drawable) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}

}
