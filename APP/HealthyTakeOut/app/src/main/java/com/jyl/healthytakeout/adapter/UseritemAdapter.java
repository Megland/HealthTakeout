package com.jyl.healthytakeout.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.entity.User;

import java.util.List;

public class UseritemAdapter extends BaseAdapter {
    private List<User> info;
    private android.content.Context Context;

    public UseritemAdapter(android.content.Context context, List<User>list){
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
        User trend=(User)info.get(position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View userview=View.inflate(Context, R.layout.item_user,null);
        TextView itemusername=userview.findViewById(R.id.itemusername);
        ImageView userprofile=userview.findViewById(R.id.userprofile);

        User item=(User)info.get(position);
        itemusername.setText(item.getUsername());
        userprofile.setImageResource(R.drawable.ic_avatar_boy);
        if(item.getGender().equals("女")){
            userprofile.setImageResource(R.drawable.ic_avatar_girl);
        }
        return userview;
    }
}
