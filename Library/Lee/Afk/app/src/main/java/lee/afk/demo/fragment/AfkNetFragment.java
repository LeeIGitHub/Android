package lee.afk.demo.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import lee.afk.afknet.AfkWifiTools;
import lee.afk.demo.R;

/**
 * Created by dahan on 2015/10/19.
 */
public class AfkNetFragment extends BaseFragment {
    @Bind(R.id.fm_tv_wifi_ssid)
    TextView mTvSSID;

    @Bind(R.id.fm_tv_wifi_mac_address)
    TextView mTvMacAddress;

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected int returnContentView() {
        return R.layout.fragment_net;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String ssid = AfkWifiTools.getWifiNowSSID(getActivity());
        if(ssid == null)
            ssid = "null";
        mTvSSID.setText(ssid);
        mTvMacAddress.setText(AfkWifiTools.getWifiMacAddress(getActivity()));
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
