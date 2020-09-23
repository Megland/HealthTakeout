package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jyl.healthytakeout.adapter.TrendAdapter;
import com.jyl.healthytakeout.entity.Trend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrendActivity extends AppCompatActivity implements View.OnClickListener, TrendAdapter.InnerItemOnClickListener{
    private ListView TrendListView;
    private FloatingActionButton AddTrendBtn;
    private ArrayList<Trend> trends = new ArrayList<>();
    private TrendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
        TrendListView=(ListView)findViewById(R.id.TrendListView);
        AddTrendBtn=(FloatingActionButton)findViewById(R.id.AddTrendBtn);
        AddTrendBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        trends.clear();
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/TrendQuery";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key","");
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
                            JSONArray jsonArray = new JSONArray(json.get("trend").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String trendnostr = jsonObject.get("trendno").toString();
                                int trendno=Integer.parseInt(trendnostr);
                                String usernostr = jsonObject.get("userno").toString();
                                int userno=Integer.parseInt(usernostr);
                                String username = (String) jsonObject.get("username");
                                String trendtitle = (String) jsonObject.get("trendtitle");
                                String trendcontent = (String) jsonObject.get("trendcontent");
                                String releasetime = (String) jsonObject.get("releasetime");
                                Trend s = new Trend(trendno,userno,username,trendtitle,trendcontent,releasetime);
                                trends.add(s);
                            }
                            adapter = new TrendAdapter(TrendActivity.this, trends);
                            TrendListView.setAdapter(adapter);
                            adapter.setOnInnerItemOnClickListener(TrendActivity.this);
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
        if(v.getId()==R.id.AddTrendBtn){
            Intent intent = new Intent();
            intent.setClass(TrendActivity.this, AddTrendActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void itemClick(View view) {
        switch (view.getId()) {
            case R.id.BtnTrendItemComment:
                int p=(Integer) view.getTag();
                Intent intent = new Intent();
                intent.setClass(this, TrendCommentActivity.class);
                intent.putExtra("trendno",trends.get(p).getTrendno());
                startActivity(intent);
                break;
        }
    }
}
