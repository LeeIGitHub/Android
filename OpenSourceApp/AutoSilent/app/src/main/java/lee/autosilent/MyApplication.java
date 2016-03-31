package lee.autosilent;

import android.app.Application;
import android.content.Intent;

import lee.afk.afkutils.log.LeeLog;
//import lee.autosilent.service.SilentService;

/**
 * Created by Lee on 2015/10/21.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
//        initService();
    }

//    private void initService(){
//        Intent intent = new Intent(this,SilentService.class);
//        startService(intent);
//    }
}
