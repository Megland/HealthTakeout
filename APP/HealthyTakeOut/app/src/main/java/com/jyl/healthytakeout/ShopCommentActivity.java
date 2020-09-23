package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jyl.healthytakeout.adapter.CommentAdpater;
import com.jyl.healthytakeout.entity.Restaurant;
import com.jyl.healthytakeout.entity.Shopcomment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCommentActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton Addshopcommentbtn;
    private ListView ShopCommentListView;
    public static ArrayList<Shopcomment> shopcomments = new ArrayList<>();
    private CommentAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_comment);
        ShopCommentListView=(ListView)findViewById(R.id.ShopCommentListView);
        Addshopcommentbtn=(FloatingActionButton)findViewById(R.id.Addshopcommentbtn);
        Addshopcommentbtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        List<Restaurant> restaurantshow=(ArrayList<Restaurant>)bundle.get("restaurantshow");
        final String key=restaurantshow.get(0).getRestaurantid();
        shopcomments.clear();
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/ShopcommentQuery";
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
                            JSONArray jsonArray = new JSONArray(json.get("shopComment").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                                String shopcommentnostr = jsonObject.get("shopcommentno").toString();
                                int shopcommentno=Integer.parseInt(shopcommentnostr);

                                String restaurantidstr = (String) jsonObject.get("restaurantid").toString();
                                int restaurantid=Integer.parseInt(restaurantidstr);

                                String content = (String) jsonObject.get("content");
                                String commenttime = (String) jsonObject.get("commenttime");

                                String usernostr = (String) jsonObject.get("userno").toString();
                                int userno=Integer.parseInt(usernostr);

                                String username = (String) jsonObject.get("username");


                                Shopcomment s = new Shopcomment(shopcommentno,restaurantid,content,commenttime,userno,username);
                                shopcomments.add(s);
                            }
                            adapter = new CommentAdpater(ShopCommentActivity.this, shopcomments);
                            ShopCommentListView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(ShopCommentActivity.this,AddShopCommentActivity.class);
        Bundle bundle = getIntent().getExtras();
        List<Restaurant> restaurantshow=(ArrayList<Restaurant>)bundle.get("restaurantshow");
        intent.putExtra("restaurantshow",(Serializable)restaurantshow);
        startActivity(intent);
    }
}
