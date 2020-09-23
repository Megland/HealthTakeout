package com.jyl.healthytakeout.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.entity.Trend;
import java.util.List;

public class TrendAdapter extends BaseAdapter implements View.OnClickListener {
    private List<Trend> info;
    private android.content.Context Context;
    private InnerItemOnClickListener mListener;

    public TrendAdapter(android.content.Context context, List<Trend>list){
        this.Context=context;
        this.info=list;
    }


    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public Object getItem(int position) {
        return info.get(position);
    }

    @Override
    public long getItemId(int position) {
        Trend trend=(Trend)info.get(position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View trendview=View.inflate(Context, R.layout.item_trend,null);

        TextView trenditemusername=trendview.findViewById(R.id.trenditemusername);
        TextView trenditemdetail=trendview.findViewById(R.id.trenditemdetail);
        TextView trenditemtime=trendview.findViewById(R.id.trenditemtime);
        TextView trenditemtitle=trendview.findViewById(R.id.trenditemtitle);

        ImageButton TrenditemComment=trendview.findViewById(R.id.BtnTrendItemComment);

        Trend item=(Trend)info.get(position);
        trenditemusername.setText(item.getUsername());
        trenditemdetail.setText(item.getTrendcontent());
        trenditemtitle.setText(item.getTrendtitle());
        trenditemtime.setText(item.getReleasetime());

        TrenditemComment.setOnClickListener(this);
        TrenditemComment.setTag(position);

        return trendview;
    }


    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
    public interface InnerItemOnClickListener {
        abstract void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnClickListener listener){
        this.mListener=listener;
    }

}
