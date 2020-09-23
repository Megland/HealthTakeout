package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jyl.healthytakeout.entity.Restaurant;
import com.jyl.healthytakeout.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jyl.healthytakeout.MainActivity.userinfo;

public class AddMessageActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText messageeditcontent;
    private Button messagesubmitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        messageeditcontent=(EditText)findViewById(R.id.message_edit_content);
        messagesubmitbtn=(Button)findViewById(R.id.messagesubmitbtn);
        messagesubmitbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.messagesubmitbtn){
            Bundle bundle = getIntent().getExtras();
            List<User> show=(ArrayList<User>)bundle.get("show");

            //final String senderuserno = commenteditcontent.getText().toString();
            final String senderuserno = Integer.toString(userinfo.get(0).getUserno());
            final String sendername=userinfo.get(0).getUsername();
            final String receiveruserno = Integer.toString(show.get(0).getUserno());
            final String messagecontent = messageeditcontent.getText().toString();
            final String sendtime = "";

            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/MessageInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("senderuserno", senderuserno);
                    params.put("sendername", sendername);
                    params.put("receiveruserno", receiveruserno);
                    params.put("messagecontent", messagecontent);
                    params.put("sendtime", sendtime);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        }
    }
}
