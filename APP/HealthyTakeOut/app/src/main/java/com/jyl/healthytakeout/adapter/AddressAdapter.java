package com.jyl.healthytakeout.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.entity.Address;
import java.util.List;

public class AddressAdapter extends BaseAdapter implements View.OnClickListener {
    private List<Address> Addressinfo;
    private android.content.Context Context;
    private InnerItemOnClickListener myListener;

    public AddressAdapter(Context context, List<Address>list){
        this.Context=context;
        this.Addressinfo=list;
    }



    @Override
    public void onClick(View v) {
        myListener.itemClick(v);
    }

    @Override
    public int getCount() {
        return Addressinfo.size();
    }

    @Override
    public Object getItem(int position) {
        return Addressinfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        Address address=(Address)Addressinfo.get(position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View addressView=View.inflate(Context, R.layout.item_address,null);
        TextView contactperson=addressView.findViewById(R.id.txt_name);
        TextView gender=addressView.findViewById(R.id.txt_gender);
        TextView phone=addressView.findViewById(R.id.txt_mobile);
        TextView addressdetail=addressView.findViewById(R.id.txt_address);
        ImageView btninneritemeditaddress=addressView.findViewById(R.id.btn_inneritem_edit_address);
        ImageView deleteaddress=addressView.findViewById(R.id.btn_delete_address);

        Address address=(Address)Addressinfo.get(position);

        contactperson.setText(address.getContactperson());
        String temp="先生";
        if(address.getGender().equals("1")){
            temp="女士";
        }
        gender.setText(temp);
        phone.setText(address.getPhone());
        addressdetail.setText(address.getAddressdetail());

        btninneritemeditaddress.setOnClickListener(this);
        deleteaddress.setOnClickListener(this);
        btninneritemeditaddress.setTag(position);
        deleteaddress.setTag(position);

        return addressView;
    }

    public interface InnerItemOnClickListener {
        abstract void itemClick(View v);
    }
    public void setOnInnerItemOnClickListener(InnerItemOnClickListener listener){
        this.myListener=listener;
    }
}
