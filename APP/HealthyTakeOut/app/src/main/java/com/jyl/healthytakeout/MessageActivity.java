package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jyl.healthytakeout.adapter.MessageAdapter;
import com.jyl.healthytakeout.adapter.TrendcommentAdapter;
import com.jyl.healthytakeout.entity.Message;
import com.jyl.healthytakeout.entity.Trendcomment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.jyl.healthytakeout.MainActivity.userinfo;

public class MessageActivity extends AppCompatActivity {
    private ListView MessageListview;
    public static ArrayList<Message> Messages = new ArrayList<>();
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        MessageListview=(ListView)findViewById(R.id.MessageListview);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        final String key=Integer.toString(userinfo.get(0).getUserno());
        Messages.clear();
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/MessageQuery";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key",key);
                String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                ///返回JSON
                android.os.Message msg = new android.os.Message();
                msg.what = 0x11;
                Bundle data = new Bundle();
                data.putString("result", result);
                System.out.println(result);
                msg.setData(data);
                handler.sendMessage(msg);
            }
            Handler handler = new Handler() {
                @Override
                public void handleMessage(android.os.Message msg) {
                    if (msg.what == 0x11) {
                        Bundle data = msg.getData();
                        String key = data.getString("result");//得到json返回的json
                        try {
                            JSONObject json = new JSONObject(key);
                            JSONArray jsonArray = new JSONArray(json.get("message").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                                String shopcommentnostr = jsonObject.get("messageid").toString();
                                int messageid=Integer.parseInt(shopcommentnostr);

                                String restaurantidstr = (String) jsonObject.get("senderuserno").toString();
                                int senderuserno=Integer.parseInt(restaurantidstr);

                                String sendername = (String) jsonObject.get("sendername");


                                String usernostr = (String) jsonObject.get("receiveruserno").toString();
                                int receiveruserno=Integer.parseInt(usernostr);

                                String messagecontent = (String) jsonObject.get("messagecontent");
                                String sendtime = (String) jsonObject.get("sendtime");

                                Message s = new Message(messageid,senderuserno,sendername,receiveruserno,messagecontent,sendtime);
                                Messages.add(s);
                            }
                            adapter = new MessageAdapter(MessageActivity.this, Messages);
                            MessageListview.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();

    }
}
