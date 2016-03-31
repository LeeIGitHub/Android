package lee.jandan.fragment;

import lee.afk.afkbase.AfkBaseFragment;
import lee.jandan.http.JandanHttp;

/**
 * Created by dahan on 2016/3/14.
 */
public abstract class BaseFragment extends AfkBaseFragment {
    @Override
    public void onDestroy() {
        JandanHttp.cancel(this);
        super.onDestroy();
    }
}
