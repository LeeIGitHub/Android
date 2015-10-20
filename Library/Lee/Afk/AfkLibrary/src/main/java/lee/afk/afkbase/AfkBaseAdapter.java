package lee.afk.afkbase;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AfkBaseAdapter<T> extends BaseAdapter {
	protected List<T> mData;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		if (convertView == null) {
			 v = mInflater.inflate(returnLayout(), parent, false);
		} else {
			v = convertView;
		}

		bindView(position, v);

		return v;
	}
	
	/**
	 * 
	 * 设置界面
	 * 
	 * @return
	 */
	protected abstract int returnLayout();

	protected abstract void bindView(int position, View v);

	/**
	 * 刷新数据
	 * @param data
	 */
	public void refresh(List<T> data){
		this.mData = data;
		notifyDataSetChanged();
	}
}
