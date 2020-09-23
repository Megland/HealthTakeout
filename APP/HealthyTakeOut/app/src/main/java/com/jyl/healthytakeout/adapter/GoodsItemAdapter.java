package com.jyl.healthytakeout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.ShoppingCartActivity;
import com.jyl.healthytakeout.entity.GoodsItem;

import java.text.NumberFormat;
import java.util.List;

public class GoodsItemAdapter extends BaseAdapter {
    private ShoppingCartActivity mContext;
    private List<GoodsItem> Datas;
    private NumberFormat nf;
    private LayoutInflater mInflater;

    public GoodsItemAdapter(ShoppingCartActivity mContext,List<GoodsItem>list){
        this.mContext=mContext;
        this.Datas=list;
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if(Datas==null){
            return 0;
        }
        return Datas.size();
    }

    @Override
    public Object getItem(int position) {
        return Datas.get(position);
    }

    @Override
    public long getItemId(int position) {
/*        GoodsItem goodsItem = (GoodsItem) Datas.get(position);*/
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder holder = null;
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.item_goods,parent,false);
            holder = new ItemViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ItemViewHolder) convertView.getTag();
        }
        GoodsItem item = Datas.get(position);
        holder.bindData(item);
        return convertView;
    }

    class ItemViewHolder implements View.OnClickListener{
        private TextView tvAdd,tvMinus,tvCount,tvName,tvPrice,calories;
        private ImageView foodimg;
        private GoodsItem item;

        public ItemViewHolder(View itemView) {
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            calories = itemView.findViewById(R.id.calories);
            tvCount = itemView.findViewById(R.id.count);
            tvMinus = itemView.findViewById(R.id.tvMinus);
            tvAdd = itemView.findViewById(R.id.tvAdd);
            tvMinus.setOnClickListener(this);
            tvAdd.setOnClickListener(this);
            foodimg=itemView.findViewById(R.id.foodimg);
        }

        public void bindData(GoodsItem item){
            this.item = item;
            tvName.setText(item.foodname);
            item.count = mContext.getSelectedItemCountById(item.foodno);
            tvCount.setText(String.valueOf(item.count));
            tvPrice.setText(nf.format(item.price));
            calories.setText(Float.toString(item.calories));
            if(item.count<1){
                tvCount.setVisibility(View.GONE);
                tvMinus.setVisibility(View.GONE);
            }else{
                tvCount.setVisibility(View.VISIBLE);
                tvMinus.setVisibility(View.VISIBLE);
            }
            foodimg.setImageResource(R.drawable.ic_restaurant);
        }

        @Override
        public void onClick(View v) {
            ShoppingCartActivity activity = mContext;
            switch (v.getId()){
                case R.id.tvAdd: {
                    int count = activity.getSelectedItemCountById(item.foodno);
                    if (count < 1) {
                        tvMinus.setAnimation(getShowAnimation());
                        tvMinus.setVisibility(View.VISIBLE);
                        tvCount.setVisibility(View.VISIBLE);
                    }
                    activity.add(item, false);
                    count++;
                    tvCount.setText(String.valueOf(count));
                    int[] loc = new int[2];
                    v.getLocationInWindow(loc);
                    activity.playAnimation(loc);
                }
                break;
                case R.id.tvMinus: {
                    int count = activity.getSelectedItemCountById(item.foodno);
                    if (count < 2) {
                        tvMinus.setAnimation(getHiddenAnimation());
                        tvMinus.setVisibility(View.GONE);
                        tvCount.setVisibility(View.GONE);
                    }
                    count--;
                    activity.remove(item, false);//activity.getSelectedItemCountById(item.id)
                    tvCount.setText(String.valueOf(count));

                }
                break;
                default:
                    break;
            }

        }
    }



    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
}
