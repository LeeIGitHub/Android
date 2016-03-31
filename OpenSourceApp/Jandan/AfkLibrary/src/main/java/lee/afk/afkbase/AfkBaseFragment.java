package lee.afk.afkbase;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import butterknife.ButterKnife;

/**
 * this fragment extends android.support.v4.app.Fragment
 */
public abstract class AfkBaseFragment extends Fragment
		implements Handler.Callback, OnClickListener{

	protected View mRootView;
	
	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = LayoutInflater.from(getActivity()).inflate(returnContentView(), container, false);

		init();
		initData(savedInstanceState);
		initView(savedInstanceState);
		return mRootView;
	}

	protected void init(){
		ButterKnife.bind(this,mRootView);
	}

	/**
	 * 
	 * 设置fragment的layout
	 * 
	 * @return
	 */
	protected abstract int returnContentView();

	/**
	 * 初始化数据
	 * @param savedInstanceState
	 */
	protected abstract void initData(Bundle savedInstanceState);

	/**
	 * 初始化控件
	 * @param savedInstanceState
	 */
	protected abstract void initView(Bundle savedInstanceState);

	/**
	 * 同 Activity.findViewById(int res)
	 * @param res
	 * @return
	 */
	protected View findViewById(int res){
		return mRootView.findViewById(res);
	}

}
