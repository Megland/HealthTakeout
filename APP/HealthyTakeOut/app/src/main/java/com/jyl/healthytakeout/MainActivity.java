package com.jyl.healthytakeout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jyl.healthytakeout.entity.Address;
import com.jyl.healthytakeout.entity.GoodsItem;
import com.jyl.healthytakeout.entity.Restaurant;
import com.jyl.healthytakeout.entity.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<Restaurant> restaurants = new ArrayList<>();
    public static ArrayList<GoodsItem> goodsItems = new ArrayList<>();
    public static ArrayList<User> userinfo = new ArrayList<>();
    public static ArrayList<Address> userAddressinfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_social, R.id.navigation_myinfo)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



        int code=getLogininfo();
        final String userNo=Integer.toString(code);
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/UserQuery";
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
                            JSONArray jsonArray = new JSONArray(json.get("user").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String Suserno = jsonObject.get("userno").toString();
                                int userno=Integer.parseInt(Suserno);
                                String username = (String) jsonObject.get("username");
                                String password = (String) jsonObject.get("password");
                                String gender = (String) jsonObject.get("gender");
                                String Sage = (String) jsonObject.get("age").toString();
                                int age=Integer.parseInt(Sage);
                                String Sheight = jsonObject.get("height").toString();
                                float height=Float.parseFloat(Sheight);
                                String Sweight = jsonObject.get("weight").toString();
                                float weight=Float.parseFloat(Sweight);
                                String STargetweight = jsonObject.get("targetweight").toString();
                                float targetweight=Float.parseFloat(STargetweight);
                                String preference = (String) jsonObject.get("preference");
                                User s = new User(userno,username,password,gender,age,height,weight,targetweight,preference);
                                userinfo.add(s);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();

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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
    }

    public int getLogininfo(){
        Bundle bundle1 = getIntent().getExtras();
        int k=bundle1.getInt("p");
        return k;
    }
}
