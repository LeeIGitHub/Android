package lee.jandan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import lee.afk.afkbase.AfkBaseAdapter;
import lee.afk.afkutils.log.LeeLog;
import lee.jandan.R;
import lee.jandan.bean.JandanPic;
import lee.jandan.bean.WuliaoPic;
import lee.jandan.activity.WuLiaoPicMatchActivity;
import lee.jandan.broadcast.BroadcastReceiverTools;
import lee.jandan.tools.GlideTools;

/**
 * Created by Lee on 2016/3/15.
 */
public class WuliaoPicAdapter extends AfkBaseAdapter<JandanPic> {
    private Context mContext;
    private Fragment mFragment;

    public WuliaoPicAdapter(Context context, List<JandanPic> data, Fragment fragment) {
        super(context, data);
        this.mContext = context;
        this.mFragment = fragment;
    }

    @Override
    protected int returnLayout() {
        return R.layout.item_pic;
    }

    @Override
    protected AfkBaseAdapter.ViewHolder returnVieHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(final int i, AfkBaseAdapter.ViewHolder viewHolder, JandanPic jandanPic) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        if (jandanPic != null) {
//            holder.ipNaiv.setDefaultImageResId(R.mipmap.ic_launcher);
//            holder.ipNaiv.setImageUrl(jandanPic.getPic());

            GlideTools.show(mContext, holder.imageView, jandanPic.getPic());

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<JandanPic> data = new ArrayList<>(getData());
                    WuLiaoPicMatchActivity.start(mContext, mFragment, data, i, v);
//
//                    ActivityOperation.startAc(mContext, TestActivity1.class);
                }
            });


            LeeLog.p(i + "   " + jandanPic.getPic());
        }
    }

    public void add(List<WuliaoPic> data) {
        if (data == null)
            return;
        if (data.size() < 1)
            return;

        List<JandanPic> jandanPicList = JandanPic.getJandan(data);

        if (jandanPicList == null || jandanPicList.size() < 1)
            return;

        getData().addAll(getCount(), jandanPicList);

        notifyDataSetChanged();

        /**
         * 广播通知 WuLiaoPicMatchActivity 数据更新了
         */
        BroadcastReceiverTools.sendBroadcast(mContext, BroadcastReceiverTools.BROADCAST_REFRESH_SUCCESS_JANDAN_WULIAO_PIC);
    }

    static class ViewHolder extends AfkBaseAdapter.ViewHolder {
        @Bind(R.id.ip_iv)
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
        }

//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
    }
}
