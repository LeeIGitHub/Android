package lee.jandan.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lee.afk.afkbase.AfkBaseAdapter;
import lee.afk.afkhttp.volley.toolbox.NetworkImageView;
import lee.afk.afkutils.log.LeeLog;
import lee.afk.view.afkimageview.NetAfkImageView;
import lee.jandan.R;
import lee.jandan.bean.JandanPic;
import lee.jandan.bean.WuliaoPic;

/**
 * Created by Lee on 2016/3/15.
 */
public class WuliaoPicAdapter extends AfkBaseAdapter<JandanPic> {

    public WuliaoPicAdapter(Context context, List<JandanPic> data) {
        super(context, data);
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
    protected void bindView(int i, AfkBaseAdapter.ViewHolder viewHolder,JandanPic jandanPic) {
        final ViewHolder holder = (ViewHolder)viewHolder;
        if (jandanPic != null) {
            holder.ipNaiv.setDefaultImageResId(R.mipmap.ic_launcher);
            holder.ipNaiv.setImageUrl(jandanPic.getPic());

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
    }


    static class ViewHolder extends AfkBaseAdapter.ViewHolder{
        @Bind(R.id.ip_naiv)
        NetAfkImageView ipNaiv;

        public ViewHolder(View view) {
            super(view);
        }

//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
    }
}
