package lee.jandan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.LinkedHashMap;
import java.util.List;

import lee.afk.afkutils.log.LeeLog;
import lee.jandan.R;
import lee.jandan.activity.BaseActivity;
import lee.jandan.activity.MainActivity;
import lee.jandan.activity.WuLiaoPicMatchActivity;
import lee.jandan.bean.JandanPic;
import lee.jandan.broadcast.BroadcastReceiverTools;
import lee.jandan.fragment.WuLiaoPicFragment;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Lee on 2016/6/12.
 */
public class WuliaoPicViewPagerAdapter extends PagerAdapter {
    private Context mContext;

    private List<JandanPic> mData;

    private LinkedHashMap<Integer, View> mViewCache;
    private View mContentView;

    public WuliaoPicViewPagerAdapter(Context context, List<JandanPic> data) {
        this.mContext = context;
        this.mData = data;

        init();
    }

    private void init() {
        mViewCache = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        if (mContentView == null)
        {
            mContentView = getItemRootView();
        }

        setView(mContentView, position);

        mViewCache.put(position, mContentView);

        container.addView(mContentView);

        loadMore(position);

        return mContentView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewCache.get(position));
        mViewCache.remove(position);
    }

    private View getItemRootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pic_match, null);
        return view;
    }

    private void setView(final View view, final int position) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.ipm_iv);
//        GlideTools.show(mContext, imageView, mData.get(position).getPic());

        Glide.with(mContext)
                .load(mData.get(position).getPic())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontTransform()
//                .thumbnail(0.1f)
                .placeholder(R.mipmap.ic_launcher)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        LeeLog.p("onResourceReady");
                        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
                        attacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                            @Override
                            public void onViewTap(View view, float v, float v1) {
                                if (mContext instanceof WuLiaoPicMatchActivity)
                                    ((WuLiaoPicMatchActivity) mContext).toggleWindowsModel();
                            }
                        });
                        int imageWidth = resource.getIntrinsicWidth();
                        int viewWidth = imageView.getWidth();

                        return false;
                    }
                })
                .into(imageView);
    }

    private void loadMore(int position) {
        if (position + 2 == mData.size()) {
            WuLiaoPicMatchActivity activity = (WuLiaoPicMatchActivity) mContext;
            BroadcastReceiverTools.sendBroadcast(mContext, BroadcastReceiverTools.BROADCAST_REFRESH_JANDAN_WULIAO_PIC);
        }
    }

    public void refresh(List<JandanPic> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
}
