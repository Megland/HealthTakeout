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

import static com.jyl.healthytakeout.MainActivity.userinfo;

public class EditUserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etinputname;
    private EditText etpassword1;
    private EditText etage1;
    private EditText etheight1;
    private EditText etweight1;
    private EditText ettargetweight1;
    private RadioGroup etgender1;
    private RadioButton boy;
    private RadioButton girl;
    private RadioGroup etpreference1;
    private RadioButton Rbtian;
    private RadioButton Rbla;
    private RadioButton Rbsuan;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        etinputname=(EditText)findViewById(R.id.et1_input_name);
        etpassword1=(EditText)findViewById(R.id.et1_password);
        etage1=(EditText)findViewById(R.id.et1_age);
        etheight1=(EditText)findViewById(R.id.et1_height);
        etweight1=(EditText)findViewById(R.id.et1_weight);
        ettargetweight1=(EditText)findViewById(R.id.et1_target_weight);
        btnUpdate=(Button)findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);

        etgender1=(RadioGroup)findViewById(R.id.et_gender);
        boy=(RadioButton)findViewById(R.id.rb1_male);
        girl=(RadioButton)findViewById(R.id.rb1_female);

        etpreference1=(RadioGroup)findViewById(R.id.et_preference);
        Rbtian=(RadioButton)findViewById(R.id.rb1_tian);
        Rbla=(RadioButton)findViewById(R.id.rb1_la);
        Rbsuan=(RadioButton)findViewById(R.id.rb1_suan);

        etinputname.setText(userinfo.get(0).getUsername());
        etpassword1.setText(userinfo.get(0).getPassword());
        etage1.setText(Integer.toString(userinfo.get(0).getAge()));
        etheight1.setText(Float.toString(userinfo.get(0).getHeight()));
        etweight1.setText(Float.toString(userinfo.get(0).getWeight()));
        ettargetweight1.setText(Float.toString(userinfo.get(0).getTargetweight()));

        if(userinfo.get(0).getGender().equals("男")){
            boy.setChecked(true);
        }else{
            girl.setChecked(true);
        }

        if(userinfo.get(0).getPreference().equals("甜")){
            Rbtian.setChecked(true);
        }else if(userinfo.get(0).getPreference().equals("酸")){
            Rbsuan.setChecked(true);
        }
        else{
            Rbla.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_update){
            final String userno = Integer.toString(userinfo.get(0).getUserno());
            final String username = etinputname.getText().toString();
            final String password = etpassword1.getText().toString();
            final String age = etage1.getText().toString();
            final String gender=getRadio(etgender1);
            final String height = etheight1.getText().toString();
            final String weight = etweight1.getText().toString();
            final String targetweight = ettargetweight1.getText().toString();
            final String preference=getRadio(etpreference1);
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
                    params.put("userno", userno);
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
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }
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
}
