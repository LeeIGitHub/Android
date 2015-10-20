package lee.afk.demo.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.RadioGroup;

import butterknife.Bind;
import lee.afk.afkutils.AfkAudioTools;
import lee.afk.demo.R;

/**
 * Created by Lee on 2015/10/19.
 */
public class AudioFragment extends BaseFragment{
    @Bind(R.id.fa_rg)
    RadioGroup mRg;

    @Override
    protected int returnContentView() {
        return R.layout.fragment_audio;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.fa_rb_silent:
                        AfkAudioTools.setRingerSilent(getActivity());
                        break;
                    case R.id.fa_rb_vibrate:
                        AfkAudioTools.setRingerVibrate(getActivity());
                        break;
                    case R.id.fa_rb_normal:
                        AfkAudioTools.setRingerNormal(getActivity());
                        break;
                }
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
