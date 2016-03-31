package lee.afk.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lee.afk.demo.R;

/**
 * Created by Lee on 2015/10/19.
 */
public class NavigationDrawerFragment extends Fragment {
    private DrawerLayout mDrawerLayout;
    private View mDrawerRootView;
    private View mFragmentContainerView;

    private ActionBarDrawerToggle mDrawerToggle;

    public interface OnDrawerItemClickListener{
        void onCLick(View v);
    }

    private OnDrawerItemClickListener onDrawerItemClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDrawerRootView = inflater.inflate(R.layout.drawer_main,container,false);
        ButterKnife.bind(this,mDrawerRootView);
        return mDrawerRootView;
    }

    @OnClick(R.id.dm_btn_afk_net)
    public void pressAfkNet(View v){
        press(v);
    }

    @OnClick(R.id.dm_btn_afk_utils)
    public void pressAfkUtils(View v){
        press(v);
    }

    @OnClick(R.id.dm_btn_afk_http2)
    public void pressAfkHttp2(View v){press(v);}

    private void press(View v){
        if(onDrawerItemClickListener != null){
            onDrawerItemClickListener.onCLick(v);
        }

        toggle();
    }


    public void setUp(int fragmentId,DrawerLayout drawerLayout){
        mFragmentContainerView = getActivity().findViewById(fragmentId);

        mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                mDrawerLayout,
                R.mipmap.ic_launcher,
                R.string.drawer_open,
                R.string.drawer_close
        ){};

    }

    public void toggle(){
        if(mDrawerLayout.isDrawerOpen(mFragmentContainerView)){
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        else
            mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public void setOnDrawerItemClickListener(OnDrawerItemClickListener listener){
        this.onDrawerItemClickListener = listener;
    }
}
