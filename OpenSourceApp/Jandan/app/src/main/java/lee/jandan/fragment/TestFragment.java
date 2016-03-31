package lee.jandan.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import butterknife.Bind;
import lee.afk.view.afkimageview.AfkGifImageView;
import lee.afk.view.afkimageview.AfkImageView;
import lee.jandan.R;

/**
 * Created by dahan on 2016/3/23.
 */
public class TestFragment extends BaseFragment {

    @Bind(R.id.ft)
    AfkImageView afkImageView;
    @Override
    protected int returnContentView() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        netAfkImageView.setImageUrl("http://ww1.sinaimg.cn/mw600/83c73248jw1f26mt0ad48j20c83qi1kx.jpg");
//        netAfkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
//        netAfkImageView.setImageUrl("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1931388891,2949581809&fm=85&s=0BA07C23E0207EBA5E4CCDD2010060A3&w=121&h=75&img.JPEG");

//        afkImageView.setTransitionAnimationEnable(false);
//        afkImageView.setImageResource(R.mipmap.chuyin);

//        afkGifImageView.setImageUrl("http://ww4.sinaimg.cn/mw690/c1a3c815gw1f27d9c3g8rg208w04y7wj.gif");
//        afkGifImageView2.setImageUrl("http://ww4.sinaimg.cn/mw690/c1a3c815gw1f27d9c3g8rg208w04y7wj.gif");
//        afkGifImageView3.setImageUrl("http://ww4.sinaimg.cn/mw690/c1a3c815gw1f27d9c3g8rg208w04y7wj.gif");
//        afkGifImageView3.setImageResource(R.mipmap.figth);
        afkImageView.setDuration(30000);

        afkImageView.setImageResource(R.mipmap.xj);
//        afkImageView.setImageResource(R.mipmap.chuyin);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
