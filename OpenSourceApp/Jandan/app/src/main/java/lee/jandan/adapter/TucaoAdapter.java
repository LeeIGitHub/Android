package lee.jandan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import lee.afk.afkbase.AfkBaseAdapter;
import lee.jandan.R;
import lee.jandan.bean.TucaoComment;

/**
 * Created by Lee on 2016/6/13.
 */
public class TucaoAdapter extends AfkBaseAdapter<TucaoComment.Tucao> {
    public TucaoAdapter(Context context, List<TucaoComment.Tucao> data) {
        super(context, data);
    }

    @Override
    protected int returnLayout() {
        return R.layout.item_tucao;
    }

    @Override
    protected ViewHolder returnVieHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void bindView(int position, AfkBaseAdapter.ViewHolder holder, TucaoComment.Tucao info) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvTucao.setText(info.getMessage());
    }

    static class ViewHolder extends AfkBaseAdapter.ViewHolder {
        @Bind(R.id.it_tv_tucao)
        TextView tvTucao;

        public ViewHolder(View view) {
            super(view);
        }

    }
}
