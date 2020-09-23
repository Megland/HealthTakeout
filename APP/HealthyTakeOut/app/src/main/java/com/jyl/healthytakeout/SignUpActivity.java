package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputname;
    private EditText password1;
    private EditText age1;
    private EditText height1;
    private EditText weight1;
    private EditText targetweight1;
    private RadioGroup gender1;
    private RadioGroup preference1;

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputname=(EditText)findViewById(R.id.et_input_name);
        password1=(EditText)findViewById(R.id.et_password);
        age1=(EditText)findViewById(R.id.et_age);
        height1=(EditText)findViewById(R.id.et_height);
        weight1=(EditText)findViewById(R.id.et_weight);
        targetweight1=(EditText)findViewById(R.id.et_target_weight);
        gender1=(RadioGroup)findViewById(R.id.gender);
        preference1=(RadioGroup)findViewById(R.id.preference);
        btnSubmit=(Button)findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
    }

    private boolean isEmpty(String str) {
        return str.length()==0||null==str;
    }

    public String getRadio(RadioGroup radioGroup){
        String info="";
        int num=radioGroup.getChildCount();
        for(int i=0;i<num;i++){
            RadioButton rd=(RadioButton)radioGroup.getChildAt(i);
            if(rd.isChecked()){
                info=rd.getText().toString();
                break;
            }
        }
        return info;
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_submit){
            final String username = inputname.getText().toString();
            final String password = password1.getText().toString();
            final String age = age1.getText().toString();
            final String gender=getRadio(gender1);
            final String height = height1.getText().toString();
            final String weight = weight1.getText().toString();
            final String targetweight = targetweight1.getText().toString();
            final String preference=getRadio(preference1);
            /*Toast.makeText(this, username+" " +password+" " +age+" " +gender+" "
                    +height+" " +weight+" "+targetweight+" "+preference, Toast.LENGTH_SHORT).show();*/
            if (isEmpty(username)) {
                Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(password)) {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(age)) {
                Toast.makeText(this, "年龄不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(gender)) {
                Toast.makeText(this, "性别不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(height)) {
                Toast.makeText(this, "身高不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(weight)) {
                Toast.makeText(this, "体重不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(targetweight)) {
                Toast.makeText(this, "目标体重不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(preference)) {
                Toast.makeText(this, "口味喜好不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/UserInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username);
                    params.put("password", password);
                    params.put("age", age);
                    params.put("gender", gender);
                    params.put("height", height);
                    params.put("weight", weight);
                    params.put("targetweight", targetweight);
                    params.put("preference", preference);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "用名："+username+"，注册成功，请返回登录！", Toast.LENGTH_SHORT).show();
        }
    }


}
