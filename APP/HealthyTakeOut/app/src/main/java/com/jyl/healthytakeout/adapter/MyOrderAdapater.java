package com.jyl.healthytakeout.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.jyl.healthytakeout.HttpUtilsHttpURLConnection;
import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.entity.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jyl.healthytakeout.MainActivity.userinfo;
import static com.jyl.healthytakeout.MyOrderActivity.adapter;

public class MyOrderAdapater extends BaseAdapter implements View.OnClickListener {
    private List<Order> Orderinfo;
    private android.content.Context Context;

    public MyOrderAdapater(android.content.Context context, List<Order>list){
        this.Context=context;
        this.Orderinfo=list;
    }

    @Override
    public void onClick(View v) {
        final int k=(Integer) v.getTag();
        switch (v.getId()) {
            case R.id.complete_order2:
                Order item=(Order)Orderinfo.get(k);
                final String orderid = Integer.toString(item.getOrderid());
                final String userno = Integer.toString(userinfo.get(0).getUserno());
                final String username = userinfo.get(0).getUsername();
                final String restaurantid = Integer.toString(item.getRestaurantid());
                final String restaurantname =item.getRestaurantname();
                final String orderdetail = item.getOrderdetails();
                final String Totalprice=Float.toString(item.getTotalprice());
                final String starttime = item.getStarttime();
                final String orderstatus = "已完成";
                final String addressno =Integer.toString(item.getAddressno());
                final String orderaddress =item.getOrderaddress();
                if(item.getOrderstatus().equals("已完成")){
                    Toast.makeText(Context, "您已经确认收货过了，无需重复确认", Toast.LENGTH_SHORT).show();
                }else {
                    new Thread() {
                        @Override
                        public void run() {
                            String url = HttpUtilsHttpURLConnection.BASE_URL + "/OrderInsertServlet";
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("orderid", orderid);
                            params.put("username", username);
                            params.put("userno", userno);
                            params.put("restaurantid", restaurantid);
                            params.put("restaurantname", restaurantname);
                            params.put("orderdetails", orderdetail);
                            params.put("totalprice", Totalprice);
                            params.put("starttime", starttime);
                            params.put("orderstatus", orderstatus);
                            params.put("addressno", addressno);
                            params.put("orderaddress", orderaddress);
                            String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                            System.out.println(result);
                        }
                    }.start();
                    item.setOrderstatus("已完成");
                    Toast.makeText(Context, "确认收货成功", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.cancel_order2:
                new AlertDialog.Builder( Context )
                        .setIcon( R.drawable.ic_delete)
                        .setTitle( "取消订单" )
                        .setMessage( "确定取消此订单？" )
                        .setNegativeButton( "取消",null )
                        .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String no=Integer.toString(Orderinfo.get(k).getOrderid());
                                new Thread() {
                                    @Override
                                    public void run() {
                                        String url = HttpUtilsHttpURLConnection.BASE_URL + "/OrderDelServlet";
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("orderid", no);
                                        String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                                        System.out.println(result);
                                    }
                                }.start();
                                Orderinfo.remove(k);
                                Toast.makeText(Context, "订单已取消", Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                            }
                        } ).show();
                break;
        }
    }

    @Override
    public int getCount() {
        return Orderinfo.size();
    }

    @Override
    public Object getItem(int position) {
        return Orderinfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        Order order=(Order)Orderinfo.get(position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View myOrderView=View.inflate(Context, R.layout.item_my_order,null);

        TextView myorderresturantname=myOrderView.findViewById(R.id.myorderresturantname);
        TextView myorderdetail=myOrderView.findViewById(R.id.myorderdetail);
        TextView myordertime=myOrderView.findViewById(R.id.myordertime);
        TextView myorderprice=myOrderView.findViewById(R.id.myorderprice);

        Button Btncompleteorder2=myOrderView.findViewById(R.id.complete_order2);
        Button Btncancelorder2=myOrderView.findViewById(R.id.cancel_order2);

        Btncancelorder2.setOnClickListener(this);
        Btncancelorder2.setTag(position);
        Btncompleteorder2.setOnClickListener(this);
        Btncompleteorder2.setTag(position);

        Order myorderlist=(Order)Orderinfo.get(position);

        myorderresturantname.setText(myorderlist.getRestaurantname());
        myorderdetail.setText(myorderlist.getOrderdetails());
        myordertime.setText(myorderlist.getStarttime());
        myorderprice.setText(Float.toString(myorderlist.getTotalprice()));

        return myOrderView;
    }
}
