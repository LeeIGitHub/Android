package lee.afk.afkutils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import java.io.File;

/**
 * Created by Lee on 2015/9/23.
 */
public class UpdateApkUtils {
    private Context mContext;
    private String mFileName;
    private String mDir;
    public UpdateApkUtils(Context context) {
        this.mContext = context;
    }

    public long downLoadFile(String httpUrl, String dir, String fileName) {
        mDir = dir;
        mFileName = fileName;

        File filePath = new File(dir);
        if(!filePath.exists()){
            filePath.mkdirs();
        }

        File file = new File(dir+"/"+fileName);
        if(file.exists())
            file.delete();

        DownloadManager manager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(httpUrl));
        request.setDestinationInExternalPublicDir(dir, fileName);

        request.setTitle("setTitle");
        request.setDescription("setDescription");

        /*android.permission.DOWNLOAD_WITHOUT_NOTIFICATION*/
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
//        request.setMimeType("application/cn.trinea.download.file");
        register(mContext);
        return manager.enqueue(request);
    }
/*//打开APK程序代码

    private void openFile(File file) {
        // TODO Auto-generated method stub
        Log.e("OpenFile", file.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }*/

    private void register(Context context){
        context.registerReceiver(onComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private BroadcastReceiver onComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            apkInstall(context,mDir,mFileName);
        }
    };

    public static void apkInstall(Context context,String dir,String file){
        String path = Environment.getExternalStoragePublicDirectory(dir).getPath();
        File f = new File(path + "/" + file);
        apkInstall(context,f);
    }

    private static void apkInstall(Context context,File file){
        MimeTypeMap map = MimeTypeMap.getSingleton();
        String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
        String type = map.getMimeTypeFromExtension(ext);

        if(type == null){
            type = "*/*";
        }
//        type = "application/vnd.android.package-archive";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), type);
        context.startActivity(intent);
    }
}
