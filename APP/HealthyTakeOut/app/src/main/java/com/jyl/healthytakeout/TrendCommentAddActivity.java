package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jyl.healthytakeout.entity.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jyl.healthytakeout.MainActivity.userinfo;

public class TrendCommentAddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText trendcommenteditcontent;
    private Button trendcommentsubmitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_comment_add);
        trendcommenteditcontent=(EditText)findViewById(R.id.trendcomment_edit_content);
        trendcommentsubmitbtn=(Button)findViewById(R.id.trendcommentsubmitbtn);
        trendcommentsubmitbtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.trendcommentsubmitbtn){
            Bundle bundle = getIntent().getExtras();
            final String key=(String) bundle.get("trendno").toString();

            final String content = trendcommenteditcontent.getText().toString();
            final String trendno = key;
            final String commenttime="";
            final String userno = Integer.toString(userinfo.get(0).getUserno());
            final String username = userinfo.get(0).getUsername();

            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/TrendcommentInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("content", content);
                    params.put("trendno", trendno);
                    params.put("commenttime", commenttime);
                    params.put("userno", userno);
                    params.put("username", username);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();

        }
    }
}
