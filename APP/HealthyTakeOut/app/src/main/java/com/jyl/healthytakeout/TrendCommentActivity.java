package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jyl.healthytakeout.adapter.TrendcommentAdapter;
import com.jyl.healthytakeout.entity.Restaurant;
import com.jyl.healthytakeout.entity.Shopcomment;
import com.jyl.healthytakeout.entity.Trendcomment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrendCommentActivity extends AppCompatActivity implements View.OnClickListener  {
    private FloatingActionButton AddTrendcommentbtn;
    private ListView TrendCommentListView;
    public static ArrayList<Trendcomment> trendcomments = new ArrayList<>();
    private TrendcommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_comment);

        TrendCommentListView=(ListView)findViewById(R.id.TrendCommentListView);
        AddTrendcommentbtn=(FloatingActionButton)findViewById(R.id.AddTrendcommentbtn);
        AddTrendcommentbtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        final String key=(String) bundle.get("trendno").toString();
        trendcomments.clear();
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/TrendcommentQuery";
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
                            JSONArray jsonArray = new JSONArray(json.get("trendcomment").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                                String shopcommentnostr = jsonObject.get("trendcommentno").toString();
                                int trendcommentno=Integer.parseInt(shopcommentnostr);

                                String restaurantidstr = (String) jsonObject.get("trendno").toString();
                                int trendno=Integer.parseInt(restaurantidstr);

                                String content = (String) jsonObject.get("content");
                                String commenttime = (String) jsonObject.get("commenttime");

                                String usernostr = (String) jsonObject.get("userno").toString();
                                int userno=Integer.parseInt(usernostr);

                                String username = (String) jsonObject.get("username");


                                Trendcomment s = new Trendcomment(trendcommentno,trendno,content,commenttime,userno,username);
                                trendcomments.add(s);
                            }
                            adapter = new TrendcommentAdapter(TrendCommentActivity.this, trendcomments);
                            TrendCommentListView.setAdapter(adapter);
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
        if(v.getId()==R.id.AddTrendcommentbtn){
            Intent intent=new Intent();
            intent.setClass(TrendCommentActivity.this,TrendCommentAddActivity.class);
            Bundle bundle = getIntent().getExtras();
            String trendno=(String) bundle.get("trendno").toString();
            intent.putExtra("trendno",trendno);
            startActivity(intent);
        }
    }
}
