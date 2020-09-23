package com.jyl.healthytakeout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private Button signup;
    private SharedPreferences loginPreference;
    private EditText userName1;
    private EditText password1;
    private TextView forgetpassword;
    private CheckBox remember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//自动生成

        View v = findViewById(R.id.back3);
        //v.getBackground().setAlpha(210);
        userName1 = (EditText) findViewById(R.id.userName);
        password1 = (EditText) findViewById(R.id.password);
        remember = (CheckBox) findViewById(R.id.remember);
        login = (Button) findViewById(R.id.login);
        signup =(Button) findViewById(R.id.signup);
        forgetpassword=(TextView)findViewById(R.id.forgetpassword);
        loginPreference = getSharedPreferences("login", MODE_PRIVATE);
        boolean checked = loginPreference.getBoolean("checked", false);
        if (checked) {
            Map<String, Object> m = readLogin();
            if (m != null) {
                userName1.setText((CharSequence) m.get("userName"));
                password1.setText((CharSequence) m.get("password"));
                remember.setChecked(checked);
            }
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configLoginInfo(remember.isChecked());
                login();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder( LoginActivity.this)
                        .setIcon( R.drawable.ic_launcher_foreground)
                        .setTitle( "忘记密码" )
                        .setMessage( "\n您可以联系管理员来重置您的密码\n\n联系方式：j59598@foxmail.com" )
                        .setNegativeButton( "取消",null )
                        .show();
            }
        });
    }

    public void configLoginInfo(boolean checked) {
        SharedPreferences.Editor editor = loginPreference.edit();
        editor.putBoolean("checked", remember.isChecked());
        if (checked) {
            editor.putString("userName", userName1.getText().toString());
            editor.putString("password", password1.getText().toString());
        } else {
            editor.remove("userName").remove("password");
        }
        editor.commit();
    }

    public Map<String, Object> readLogin() {
        Map<String, Object> m = new HashMap<>();
        String userName = loginPreference.getString("userName", "");
        String password = loginPreference.getString("password", "");
        m.put("userName", userName);
        m.put("password", password);
        return m;
    }

    private void login() {
        new Thread() {
            @Override
            public void run() {
                //System.out.println("登录成功");
                Map<String, String> params = new HashMap<String, String>();
                String userName = userName1.getText().toString();
                String password = password1.getText().toString();
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/LoginUserServlet";
                params.put("password", password);
                params.put("username", userName);
                String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                ///返回JSON
                //Toast.makeText(LoginActivity.this, "链接失败！", Toast.LENGTH_LONG).show();
                Message msg = new Message();
                msg.what = 0x11;
                Bundle data = new Bundle();
                data.putString("result", result);
                msg.setData(data);
                handler.sendMessage(msg);
            }

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x11) {
                        Bundle data = msg.getData();
                        String key = data.getString("result");//得到json返回的json
                        System.out.println(key);
                        try {
                            JSONObject json = new JSONObject(key);
                            int code = Integer.parseInt(json.getString("code"));
                            System.out.println(code);

                            if (code != 0) {
                                /*Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();*/
                                Intent intent = new Intent();
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setClass(LoginActivity.this, MainActivity.class);
                                sentI(intent,code);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
    }
    public void sentI(Intent intent,int p){
        intent.putExtra("p",p);
    }
}
