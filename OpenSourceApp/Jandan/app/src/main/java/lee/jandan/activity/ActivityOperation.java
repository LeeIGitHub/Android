package lee.jandan.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Lee on 2016/6/15.
 */
public class ActivityOperation {
    public static void startAc(Context context, Class c) {
        Intent intent = new Intent(context, c);
        startAc(context, intent);
    }

    public static void startAc(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
            }
        } else {
            context.startActivity(intent);
        }
    }

    public static void startAcWithView(Context context, Intent intent, View androidRobotView, String transitionName, int requestCode) {
        startAcWithView(context, null, intent, androidRobotView, transitionName, requestCode);
    }

    /**
     * @param context
     * @param intent
     * @param androidRobotView
     * @param transitionName
     * @param requestCode      如果不需要 startActivityForResult requestCode=0 即可
     */
    public static void startAcWithView(Context context, Fragment fragment, Intent intent, View androidRobotView, String transitionName, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, androidRobotView, transitionName);
                if (requestCode == 0)
                    context.startActivity(intent, options.toBundle());
                else {
                    if (fragment != null)
                        fragment.startActivityForResult(intent, requestCode, options.toBundle());
                    else
                        ((Activity) context).startActivityForResult(intent, requestCode, options.toBundle());
                }
            }
        } else {
            context.startActivity(intent);
        }
    }
}
