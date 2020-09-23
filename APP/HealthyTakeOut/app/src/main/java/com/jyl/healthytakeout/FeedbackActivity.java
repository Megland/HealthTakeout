package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import static com.jyl.healthytakeout.MainActivity.userinfo;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private Button commentsubmit;
    private TextView feedbackeditcontent;
    private TextView contactmethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedbackeditcontent=(TextView)findViewById(R.id.feedback_edit_content);
        contactmethod=(TextView)findViewById(R.id.contact_method);
        commentsubmit=(Button)findViewById(R.id.commentsubmit);
        commentsubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.commentsubmit){
            final String username = userinfo.get(0).getUsername();
            final String userno = Integer.toString(userinfo.get(0).getUserno());
            final String contactdetail = contactmethod.getText().toString();
            final String feedbackdetail = feedbackeditcontent.getText().toString();

            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/FeedbackInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username);
                    params.put("userno", userno);
                    params.put("contactdetail", contactdetail);
                    params.put("feedbackdetail", feedbackdetail);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "反馈成功", Toast.LENGTH_SHORT).show();
        }
    }
}
