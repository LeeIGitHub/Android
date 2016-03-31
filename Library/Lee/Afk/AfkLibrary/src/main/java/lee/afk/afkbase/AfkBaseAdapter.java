package lee.afk.afkbase;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import butterknife.ButterKnife;

public abstract class AfkBaseAdapter<T> extends BaseAdapter {
	private List<T> mData;
	protected LayoutInflater mInflater;

	public AfkBaseAdapter(Context context, List<T> data) {
		this.mData = data;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if (mData == null)
			return 0;
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		if (mData == null || mData.size() == 0)
			return null;
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if (convertView == null) {
			 convertView = mInflater.inflate(returnLayout(), parent, false);
			holder = returnVieHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}

		bindView(position, holder,getData().get(position));

		return convertView;
	}
	
	/**
	 * 
	 * 设置界面
	 * 
	 * @return
	 */
	protected abstract int returnLayout();

	protected abstract ViewHolder returnVieHolder(View view);

	protected abstract void bindView(int position, ViewHolder holder,T info);

	/**
	 * 刷新数据
	 * @param data
	 */
	public void refresh(List<T> data){
		this.mData = data;
		notifyDataSetChanged();
	}

	/**
	 * 获取数据
	 * @return
	 */
	public List<T> getData(){
		if(mData == null){
			mData = new ArrayList<>();
		}
		return mData;
	}


	public static class ViewHolder{
		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}
}
