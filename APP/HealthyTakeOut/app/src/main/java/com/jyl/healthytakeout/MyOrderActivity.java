package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import com.jyl.healthytakeout.adapter.MyOrderAdapater;
import com.jyl.healthytakeout.entity.Order;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.jyl.healthytakeout.MainActivity.userinfo;

public class MyOrderActivity extends AppCompatActivity {
    private ListView MyOrderListView;
    private ArrayList<Order> orders = new ArrayList<>();
    public static MyOrderAdapater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        MyOrderListView=(ListView)findViewById(R.id.MyOrderListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final String key=Integer.toString(userinfo.get(0).getUserno());

        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/OrderQuery";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key",key);
                String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                ///返回JSON
                Message msg = new Message();
                msg.what = 0x11;
                Bundle data = new Bundle();
                data.putString("result", result);
                System.out.println(result);
                msg.setData(data);
                handler.sendMessage(msg);
            }
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x11) {
                        Bundle data = msg.getData();
                        String key = data.getString("result");//得到json返回的json
                        try {
                            JSONObject json = new JSONObject(key);
                            JSONArray jsonArray = new JSONArray(json.get("order").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String orderidstr = jsonObject.get("orderid").toString();
                                int orderid=Integer.parseInt(orderidstr);

                                String usernostr = jsonObject.get("userno").toString();
                                int userno=Integer.parseInt(usernostr);

                                String restaurantidstr = jsonObject.get("restaurantid").toString();
                                int restaurantid=Integer.parseInt(restaurantidstr);

                                String username = (String) jsonObject.get("username");
                                String restaurantname = (String) jsonObject.get("restaurantname");
                                String orderdetails = (String) jsonObject.get("orderdetails");

                                String totalpricestr = (String) jsonObject.get("totalprice").toString();
                                float totalprice=Float.parseFloat(totalpricestr);

                                String starttime = (String) jsonObject.get("starttime");
                                String orderstatus = (String) jsonObject.get("orderstatus");

                                String addressnostr = (String) jsonObject.get("addressno").toString();
                                int addressno=Integer.parseInt(addressnostr);

                                String orderaddress = (String) jsonObject.get("orderaddress");
                                Order s = new Order(orderid,userno,restaurantid,username,restaurantname,orderdetails,totalprice,starttime,orderstatus,addressno,orderaddress);
                                orders.add(s);
                            }
                            adapter = new MyOrderAdapater(MyOrderActivity.this, orders);
                            MyOrderListView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
    }
}
