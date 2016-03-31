package lee.autosilent.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2015/11/6.
 */
public abstract class MyBaseRecycleAdapter<T extends RecyclerView.ViewHolder,D> extends RecyclerView.Adapter<T>{

    private List<D> mData;
    public MyBaseRecycleAdapter(List<D> data){
        this.mData = data;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        D data = null;
        if(mData != null)
            data = mData.get(position);
        onBindViewHolder(holder,position,data);
    }

    @Override
    public int getItemCount() {
        if(mData == null)
            return 0;

        return mData.size();
    }

    public abstract void onBindViewHolder(T holder, int position,D data);

    public void refresh(List<D> data){
        mData = data;
        notifyDataSetChanged();
    }

    public List<D> getData(){
        return mData;
    }

    public D getData(int position){
        return mData.get(position);
    }

    public void add(D data){
        if(mData == null)
            mData = new ArrayList<>();

        int position = mData.size();
        mData.add(data);
        notifyItemInserted(position);
    }

    public void add(int position , D data){
        if(mData == null)
            mData = new ArrayList<>();

        mData.add(position,data);
        notifyItemInserted(position);
    }

    public void remove(int position){
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
