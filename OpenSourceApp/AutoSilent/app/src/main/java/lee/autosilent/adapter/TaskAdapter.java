package lee.autosilent.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lee.afk.afkbase.AfkBaseAdapter;
import lee.autosilent.R;
import lee.autosilent.bean.Task;

/**
 * Created by dahan on 2015/10/21.
 */
public class TaskAdapter extends AfkBaseAdapter {
    public TaskAdapter(Context context, List<Task> data) {
        super(context, data);
    }

    @Override
    protected void bindView(int position, View v) {
        Task task = (Task)mData.get(position);
        TextView tvssid = (TextView)v.findViewById(R.id.it_tv_ssid);
        TextView tvmode = (TextView)v.findViewById(R.id.it_tv_mode);

        tvssid.setText(task.getSsid());
        tvmode.setText(task.getMode()+"");
    }

    @Override
    protected int returnLayout() {
        return R.layout.item_task;
    }
}
