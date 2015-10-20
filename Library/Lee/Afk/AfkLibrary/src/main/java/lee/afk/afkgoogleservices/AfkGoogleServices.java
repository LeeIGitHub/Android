package lee.afk.afkgoogleservices;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import lee.afk.afkutils.log.LeeLog;

/**
 * Created by Lee on 2015/9/11.
 */
public class AfkGoogleServices implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    private static AfkGoogleServices mInstance;

    private Activity mActivity;
    private Context mContext;
    private GoogleApiClient mGoogleApiClient;

    public static AfkGoogleServices onStart(Context context){
        if(mInstance == null){
            mInstance = new AfkGoogleServices(context);
        }
        return mInstance;
    }

    public static void onStop(){
        if(mInstance == null)
            return ;
        mInstance.mGoogleApiClient.disconnect();
    }

    private AfkGoogleServices(Context context) {
        this.mContext = context;
        init();
    }

    @Override
    public void onConnected(Bundle bundle) {
        String msg = bundle== null ? "null " : bundle.toString();
        LeeLog.p("onConnected   " + msg);
    }

    @Override
    public void onConnectionSuspended(int i) {
        LeeLog.p("onConnected   " + i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        LeeLog.p("onConnected   " + connectionResult.toString());
    }

    private void init(){
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }
}
