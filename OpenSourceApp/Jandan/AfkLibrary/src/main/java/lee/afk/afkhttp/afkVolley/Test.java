package lee.afk.afkhttp.afkVolley;

import android.content.Context;

import lee.afk.afkutils.log.LeeLog;

/**
 * Created by dahan on 2015/9/2.
 */
public class Test {
    //http://m.dahanis.com:24090/AppUserService.asmx/GetPhoneCode4RegisterV1?&phone=13122279801&userType=1&

    public static void getNumber(Context context){
        String url = "http://m.dahanis.com:24090/AppUserService.asmx/GetPhoneCode4RegisterV1?&phone=13122279801&userType=1&";

        LeeHttpUtil.getData(context, url, new LeeHttpUtil.LeeHttpRequestListener<TestBean>() {
            @Override
            public void onSuccess(TestBean var) {
                super.onSuccess(var);
                LeeLog.p(var.toString());
            }

            @Override
            public void onFailed(AfkHttpError error) {
                super.onFailed(error);

            }

            @Override
            public void onFinished() {
                super.onFinished();

            }
        });
    }
}
