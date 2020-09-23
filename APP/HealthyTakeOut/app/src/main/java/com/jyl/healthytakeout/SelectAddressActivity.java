package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jyl.healthytakeout.adapter.SelectAddressAdapter;
import com.jyl.healthytakeout.entity.Address;
import com.jyl.healthytakeout.entity.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jyl.healthytakeout.MainActivity.userAddressinfo;
import static com.jyl.healthytakeout.MainActivity.userinfo;
import static com.jyl.healthytakeout.ShoppingCartActivity.selectedList;

public class SelectAddressActivity extends AppCompatActivity implements  AdapterView.OnItemClickListener{
    private ListView listView;
    public static SelectAddressAdapter adapter;
    private List<Address> show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        listView = findViewById(R.id.Selectaddresslistview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        List<Restaurant> restaurantshow=(ArrayList<Restaurant>)bundle.get("restaurantshow");
        userAddressinfo.clear();
        final String userNo=Integer.toString(userinfo.get(0).getUserno());
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/AddressQuery";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key",userNo);
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
                            JSONArray jsonArray = new JSONArray(json.get("address").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String Saddressno = jsonObject.get("addressno").toString();
                                int addressno=Integer.parseInt(Saddressno);
                                String contactperson = (String) jsonObject.get("contactperson");
                                String gender = (String) jsonObject.get("gender");
                                String phone = (String) jsonObject.get("phone");
                                String addressdetail = (String) jsonObject.get("addressdetail");
                                String Suserno = (String) jsonObject.get("userno").toString();
                                int userno=Integer.parseInt(Suserno);
                                Address s = new Address(addressno,contactperson,gender,phone,addressdetail,userno);
                                userAddressinfo.add(s);
                            }
                            adapter = new SelectAddressAdapter(SelectAddressActivity.this, userAddressinfo);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        show=new ArrayList<Address>();
        Intent intent=new Intent();
        int addressno=userAddressinfo.get(i).getAddressno();
        String contactperson=userAddressinfo.get(i).getContactperson();
        String gender=userAddressinfo.get(i).getGender();
        String phone=userAddressinfo.get(i).getPhone();
        String addressdetail=userAddressinfo.get(i).getAddressdetail();
        int userno=userinfo.get(0).getUserno();
        Address address=new Address(addressno,contactperson,gender,phone,addressdetail,userno);
        show.add(address);

        Bundle bundle = getIntent().getExtras();
        List<Restaurant> restaurantshow=(ArrayList<Restaurant>)bundle.get("restaurantshow");
        intent.setClass(SelectAddressActivity.this,CreateOrderActivity.class);
        intent.putExtra("restaurantshow",(Serializable)restaurantshow);
        intent.putExtra("show",(Serializable)show);
        startActivity(intent);
    }
}
