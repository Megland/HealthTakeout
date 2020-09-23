package com.jyl.healthytakeout.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.entity.Message;
import com.jyl.healthytakeout.entity.Trendcomment;

import java.util.List;

public class MessageAdapter  extends BaseAdapter {

    private List<Message> infolist;
    private android.content.Context Context;

    public MessageAdapter(android.content.Context context, List<Message>list){
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
        Message Shopcomments=(Message)infolist.get(position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View commentView=View.inflate(Context, R.layout.item_comment,null);
        TextView newscommentcontent=commentView.findViewById(R.id.txt_comment);
        TextView newscommentuser=commentView.findViewById(R.id.txt_comment_user);
        TextView newscommenttime=commentView.findViewById(R.id.txt_comment_time);

        Message item=(Message)infolist.get(position);

        newscommentcontent.setText(item.getMessagecontent());
        newscommentuser.setText(item.getSendername());
        newscommenttime.setText("在"+item.getSendtime()+"给您留言");

        return commentView;
    }
}
