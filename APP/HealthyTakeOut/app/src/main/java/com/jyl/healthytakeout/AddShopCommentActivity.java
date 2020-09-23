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

public class AddShopCommentActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText commenteditcontent;
    private Button commentsubmitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop_comment);
        commenteditcontent=(EditText)findViewById(R.id.comment_edit_content);
        commentsubmitbtn=(Button)findViewById(R.id.commentsubmitbtn);
        commentsubmitbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.commentsubmitbtn){
            Bundle bundle = getIntent().getExtras();
            List<Restaurant> restaurantshow=(ArrayList<Restaurant>)bundle.get("restaurantshow");

            final String content = commenteditcontent.getText().toString();
            final String restaurantid = restaurantshow.get(0).getRestaurantid();
            final String commenttime="";
            final String userno = Integer.toString(userinfo.get(0).getUserno());
            final String username = userinfo.get(0).getUsername();

            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/ShopCommentInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("content", content);
                    params.put("restaurantid", restaurantid);
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
