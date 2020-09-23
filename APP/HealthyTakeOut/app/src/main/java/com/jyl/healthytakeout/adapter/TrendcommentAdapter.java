package com.jyl.healthytakeout.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.entity.Trendcomment;

import java.util.List;

public class TrendcommentAdapter extends BaseAdapter {
    private List<Trendcomment> infolist;
    private android.content.Context Context;

    public TrendcommentAdapter(android.content.Context context, List<Trendcomment>list){
        this.Context=context;
        this.infolist=list;
    }

    @Override
    public int getCount() {
        return infolist.size();
    }

    @Override
    public Object getItem(int position) {
        return infolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        Trendcomment Shopcomments=(Trendcomment)infolist.get(position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View commentView=View.inflate(Context, R.layout.item_comment,null);
        TextView newscommentcontent=commentView.findViewById(R.id.txt_comment);
        TextView newscommentuser=commentView.findViewById(R.id.txt_comment_user);
        TextView newscommenttime=commentView.findViewById(R.id.txt_comment_time);

        Trendcomment item=(Trendcomment)infolist.get(position);

        newscommentcontent.setText(item.getContent());
        newscommentuser.setText(item.getUsername());
        newscommenttime.setText(item.getCommenttime());

        return commentView;
    }
}
