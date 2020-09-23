package com.jyl.healthytakeout.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.jyl.healthytakeout.HttpUtilsHttpURLConnection;
import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.entity.Restaurant;

import java.util.List;

public class RestaurantAdapter extends BaseAdapter implements View.OnClickListener {
    private List <Restaurant>restaurantinfo;
    private Context Context;
    private InnerItemOnClickListener myListener;
    public RestaurantAdapter(Context context, List<Restaurant>list){
        this.Context=context;
        this.restaurantinfo=list;
    }
    @Override
    public int getCount() {
        return restaurantinfo.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantinfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        Restaurant restaurant=(Restaurant)restaurantinfo.get(position);
        return position;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View restaurantView=View.inflate(Context, R.layout.restaurant_item,null);
        TextView restaurantName=restaurantView.findViewById(R.id.restaurant_name);
        TextView restaurantintro=restaurantView.findViewById(R.id.restaurant_introduction);
        ImageView restaurantimage=restaurantView.findViewById(R.id.restaurant_image);

        Restaurant restaurant=(Restaurant)restaurantinfo.get(i);
        restaurantName.setText(restaurant.getRestaurantname());
        restaurantintro.setText(restaurant.getIntroduction());
        String imageurl= HttpUtilsHttpURLConnection.BASE_URL+"/image/restaurant/"+restaurant.getRestaurantid()+".png";
        Glide.with(Context).load(imageurl).placeholder(R.drawable.ic_restaurant).error(R.drawable.ic_restaurant).into(restaurantimage);
        //restaurantimage.setImageResource(R.drawable.ic_restaurant);
        return restaurantView;
    }
    interface InnerItemOnClickListener {
        abstract void itemClick(View view);
    }
    public void setOnInnerItemOnClickListener(InnerItemOnClickListener listener){
        this.myListener=listener;
    }
    @Override
    public void onClick(View view) {
        myListener.itemClick(view);
    }

}
