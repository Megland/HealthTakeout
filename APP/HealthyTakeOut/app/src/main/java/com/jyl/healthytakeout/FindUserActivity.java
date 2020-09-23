package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.jyl.healthytakeout.adapter.TrendAdapter;
import com.jyl.healthytakeout.adapter.UseritemAdapter;
import com.jyl.healthytakeout.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindUserActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText finduser;
    private ImageButton searchuser;
    private ListView FindUserListView;
    public static ArrayList<User> userSearchResults = new ArrayList<>();
    private UseritemAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        finduser=(EditText)findViewById(R.id.finduser);
        searchuser=(ImageButton) findViewById(R.id.searchuser);
        FindUserListView=(ListView) findViewById(R.id.FindUserListView);
        searchuser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.searchuser){
            userSearchResults.clear();
            final String key=finduser.getText().toString();
            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/UserQuery";
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
                                    userSearchResults.add(s);
                                }
                                adapter = new UseritemAdapter(FindUserActivity.this, userSearchResults);
                                FindUserListView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            }.start();
            FindUserListView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        List<User> show=new ArrayList<User>();
        Intent intent = new Intent();

        int userno=userSearchResults.get(position).getUserno();
        String username=userSearchResults.get(position).getUsername();
        String password=userSearchResults.get(position).getPassword();
        String gender=userSearchResults.get(position).getGender();
        int age=userSearchResults.get(position).getAge();
        float height=userSearchResults.get(position).getHeight();
        float weight=userSearchResults.get(position).getWeight();
        float targetweight=userSearchResults.get(position).getTargetweight();
        String preference=userSearchResults.get(position).getPreference();


        User useritem=new User(userno,username,password,gender,age,height,weight,targetweight,preference);
        show.add(useritem);
        intent.setClass(FindUserActivity.this, PersonalPageActivity.class);
        intent.putExtra("show",(Serializable)show);
        startActivity(intent);
    }
}
