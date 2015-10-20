package lee.afk.afkbase;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AfkBaseViewPagerAdapter extends PagerAdapter{
	
	private List<? extends View> mViewList;
	
	private List<ImageView> mPointList;
	
	public AfkBaseViewPagerAdapter(List<? extends View> views) {
		this.mViewList = views;
	}

	@Override
	public int getCount() {
		if(mViewList == null){
			return 0;
		}
		return mViewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = mViewList.get(position);
		container.addView(view);
		return view;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mViewList.get(position));
	}
	
	/**
	 * 设置小圆点
	 * @param viewPager
	 * @param normal
	 * @param select
	 */
	public void setPoint(ViewPager viewPager,final int normal,final int select){
		setPoint(viewPager, normal, select, null);
	}
	
	/**
	 * 
	 * 设置小圆点
	 * 
	 * @param viewPager
	 * @param normal
	 * @param select
	 * @param pointLayout
	 */
	public void setPoint(ViewPager viewPager,final int normal,final int select,LinearLayout pointLayout){
		buildPoint(viewPager, normal, select, pointLayout);
		mPointList.get(0).setImageResource(select);
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				for (ImageView imageView : mPointList) {
					imageView.setImageResource(normal);
				}
				mPointList.get(arg0).setImageResource(select);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	
	/**
	 * 
	 * 生成小圆点
	 * 
	 * @param viewPager
	 * @param normal
	 * @param select
	 * @param pointLayout
	 */
	private void buildPoint(ViewPager viewPager,int normal,int select,LinearLayout pointLayout){
		pointLayout = buildPointLayout(viewPager, pointLayout);
		buildPointButton(pointLayout, normal, select, getCount());
	}
	
	/**
	 * 生成pointLayout
	 * @param viewPager
	 * @param pointLayout
	 * @return
	 */
	private LinearLayout buildPointLayout(ViewPager viewPager,LinearLayout pointLayout){
		ViewGroup parentLayout = ((ViewGroup)viewPager.getParent());
		if(pointLayout == null){
			pointLayout = new LinearLayout(viewPager.getContext());
			
			//若viewPager的父控件为 相对布局，则将pointLayout放在底部
			if(parentLayout instanceof RelativeLayout){
				
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
				params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
				pointLayout.setLayoutParams(params);
			}
			parentLayout.addView(pointLayout);
		}
		else{

		}
		//使 所有的小圆点都在中间
		pointLayout.setGravity(Gravity.CENTER);
		
		return pointLayout;
	}
	
	/**
	 * 生成小圆点按钮
	 * @param pointLayout
	 * @param normal
	 * @param select
	 * @param size
	 */
	private void buildPointButton(LinearLayout pointLayout,int normal,int select,int size){
		mPointList = new ArrayList<ImageView>(size);
		ImageView point = null;
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		for(int i = 0; i<size; i ++){
			point = new ImageView(pointLayout.getContext());
			
			point.setImageResource(normal);
//			point.setBackgroundResource(normal);
			pointLayout.addView(point, params);
			
			mPointList.add(point);
		}
	}
}
