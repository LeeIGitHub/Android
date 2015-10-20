package lee.afk.demo.fragment;

import android.support.v4.app.Fragment;

import lee.afk.afkbase.AfkBaseFragment;

/**
 * Created by dahan on 2015/10/19.
 */
public abstract class BaseFragment extends AfkBaseFragment {

    protected void addFragment(int container,Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction().add(container,fragment).commit();
    }

    protected void replaceFragment(int container,Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction().replace(container,fragment).commit();
    }
}
