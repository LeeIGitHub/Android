package lee.jandan.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by Lee on 2016/6/23.
 */
public class BroadcastReceiverTools {
    public static final String BROADCAST_REFRESH_JANDAN_WULIAO_PIC = "qushuaxin";
    public static final String BROADCAST_REFRESH_SUCCESS_JANDAN_WULIAO_PIC = "shuaxinwuliaotuchenggong";

    /**
     * 发送广播
     */
    public synchronized static void sendBroadcast(Context context, String broadcast_id) {
        Intent intent = new Intent();
        intent.setAction(broadcast_id);
        context.sendOrderedBroadcast(intent, null);
    }

    /**
     * 注册广播
     * @param context
     * @param receiver
     * @param broadcast_id
     */
    public synchronized static void registerReceiver(Context context, BroadcastReceiver receiver, String broadcast_id) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(broadcast_id);
        context.registerReceiver(receiver, filter);
    }


}
