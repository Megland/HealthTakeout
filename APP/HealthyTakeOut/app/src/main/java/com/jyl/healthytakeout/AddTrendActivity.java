package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static com.jyl.healthytakeout.MainActivity.userinfo;

public class AddTrendActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addtrendsubmit;
    private EditText addtrendtitle;
    private EditText addtrendcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trend);
        addtrendtitle=(EditText)findViewById(R.id.addtrendtitle);
        addtrendcontent=(EditText)findViewById(R.id.addtrendcontent);
        addtrendsubmit=(Button)findViewById(R.id.addtrendsubmit);
        addtrendsubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addtrendsubmit){
            final String username = userinfo.get(0).getUsername();
            final String userno = Integer.toString(userinfo.get(0).getUserno());
            final String trendtitle = addtrendtitle.getText().toString();
            final String trendcontent = addtrendcontent.getText().toString();
            final String releasetime = "";

            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/TrendInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username);
                    params.put("userno", userno);
                    params.put("trendtitle", trendtitle);
                    params.put("trendcontent", trendcontent);
                    params.put("releasetime", releasetime);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        }
    }


}
