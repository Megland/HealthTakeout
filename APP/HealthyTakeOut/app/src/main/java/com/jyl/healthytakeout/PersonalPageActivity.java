package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jyl.healthytakeout.adapter.TrendAdapter;
import com.jyl.healthytakeout.adapter.UseritemAdapter;
import com.jyl.healthytakeout.entity.Focus;
import com.jyl.healthytakeout.entity.Trend;
import com.jyl.healthytakeout.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jyl.healthytakeout.MainActivity.userinfo;

public class PersonalPageActivity extends AppCompatActivity implements View.OnClickListener,TrendAdapter.InnerItemOnClickListener{
    private ImageView ppuserAvatar;
    private TextView ppusername;
    private TextView ppgender;
    private TextView ppage;
    private ImageButton ppaddfoucs;
    private ImageButton ppsendmessage;
    private TrendAdapter adapter;
    private ListView pptrendlistview;
    public static ArrayList<Focus> focusresult = new ArrayList<>();
    private ArrayList<Trend> trends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);
        ppuserAvatar=(ImageView)findViewById(R.id.ppuserAvatar);
        ppusername=(TextView)findViewById(R.id.ppusername);
        ppgender=(TextView)findViewById(R.id.ppgender);
        ppage=(TextView)findViewById(R.id.ppage);

        ppaddfoucs=(ImageButton)findViewById(R.id.ppaddfoucs);
        ppsendmessage=(ImageButton)findViewById(R.id.ppsendmessage);
        pptrendlistview=(ListView)findViewById(R.id.pptrendlistview);

        ppaddfoucs.setOnClickListener(this);
        ppsendmessage.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        List<User> show=(ArrayList<User>)bundle.get("show");

        ppusername.setText(show.get(0).getUsername());
        ppgender.setText(show.get(0).getGender());
        ppage.setText(Integer.toString(show.get(0).getAge()));

        if(show.get(0).getGender().equals("女")){
            ppuserAvatar.setImageResource(R.drawable.ic_avatar_girl);
        }

        final String key1=Integer.toString(userinfo.get(0).getUserno());
        final String key2=Integer.toString(show.get(0).getUserno());

        focusresult.clear();
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/CheckFocusServlet";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key",key1);
                params.put("key2",key2);
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
                            JSONArray jsonArray = new JSONArray(json.get("focus").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String focusids = jsonObject.get("focusid").toString();
                                int focusid=Integer.parseInt(focusids);
                                String usernos = jsonObject.get("userno").toString();
                                int userno=Integer.parseInt(usernos);
                                String focususernos = jsonObject.get("focususerno").toString();
                                int focususerno=Integer.parseInt(focususernos);
                                Focus s = new Focus(focusid,userno,focususerno);
                                focusresult.add(s);
                                if(focusresult!=null){
                                    ppaddfoucs.setImageResource(R.drawable.ic_alreadyfocus);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();

        trends.clear();
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/TrendQuery";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key",key2);
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
                            adapter = new TrendAdapter(PersonalPageActivity.this, trends);
                            pptrendlistview.setAdapter(adapter);
                            adapter.setOnInnerItemOnClickListener(PersonalPageActivity.this);
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
        if(v.getId()==R.id.ppaddfoucs){
            final String userno=Integer.toString(userinfo.get(0).getUserno());
            Bundle bundle = getIntent().getExtras();
            List<User> show=(ArrayList<User>)bundle.get("show");
            final String fansuserno=Integer.toString(show.get(0).getUserno());
            if(focusresult.size()==0){
                new Thread() {
                    @Override
                    public void run() {
                        String url = HttpUtilsHttpURLConnection.BASE_URL + "/FansInsertServlet";
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("userno", userno);
                        params.put("fansuserno", fansuserno);
                        String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                        System.out.println(result);
                    }
                }.start();
                ppaddfoucs.setImageResource(R.drawable.ic_alreadyfocus);
                Toast.makeText(this, "已关注", Toast.LENGTH_SHORT).show();
                Focus s = new Focus(0,0,0);
                focusresult.add(s);
            }else{
                new Thread() {
                    @Override
                    public void run() {
                        String url = HttpUtilsHttpURLConnection.BASE_URL + "/CancelFocus";
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("userno", userno);
                        params.put("userno2", fansuserno);
                        String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                        System.out.println(result);
                    }
                }.start();
                ppaddfoucs.setImageResource(R.drawable.ic_addfocus);
                Toast.makeText(this, "已取消关注", Toast.LENGTH_SHORT).show();
                focusresult.clear();
            }

        }
        if(v.getId()==R.id.ppsendmessage){
            Intent intent = new Intent();
            Bundle bundle = getIntent().getExtras();
            List<User> show=(ArrayList<User>)bundle.get("show");
            intent.setClass(PersonalPageActivity.this, AddMessageActivity.class);
            intent.putExtra("show",(Serializable)show);
            startActivity(intent);
        }
    }

    @Override
    public void itemClick(View v) {
        switch (v.getId()) {
            case R.id.BtnTrendItemComment:
                int p=(Integer) v.getTag();
                Intent intent = new Intent();
                intent.setClass(this, TrendCommentActivity.class);
                intent.putExtra("trendno",trends.get(p).getTrendno());
                startActivity(intent);
                break;
        }
    }
}
