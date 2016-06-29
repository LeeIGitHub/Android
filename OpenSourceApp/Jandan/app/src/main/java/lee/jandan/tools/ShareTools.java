package lee.jandan.tools;

import android.content.Context;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Lee on 2016/6/23.
 */
public class ShareTools {
    public static void showShare(Context context,String url){
        ShareSDK.initSDK(context);
        OnekeyShare onekeyShare = new OnekeyShare();
        //关闭sso授权
        onekeyShare.disableSSOWhenAuthorize();

        onekeyShare.setUrl(url);
        onekeyShare.show(context);
    }
}
