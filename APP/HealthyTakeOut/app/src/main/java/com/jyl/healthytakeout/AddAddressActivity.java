package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etcontactperson;
    private RadioGroup Cpgender;
    private EditText etcpinputphone;
    private EditText etaddressdetail;
    private Button btnAddAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        etcontactperson=(EditText)findViewById(R.id.et_contact_person);
        etcpinputphone=(EditText)findViewById(R.id.et_cp_input_phone);
        etaddressdetail=(EditText)findViewById(R.id.et_addressdetail);
        Cpgender=(RadioGroup)findViewById(R.id.Cp_gender);
        btnAddAddress=(Button)findViewById(R.id.btn_add_address);
        btnAddAddress.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId()==R.id.btn_add_address){
            final String contactperson = etcontactperson.getText().toString();
            final String gender = getRadio(Cpgender);
            final String phone = etcpinputphone.getText().toString();
            final String addressdetail = etaddressdetail.getText().toString();
            final String userno = Integer.toString(userinfo.get(0).getUserno());

            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/AddressInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("contactperson", contactperson);
                    params.put("gender", gender);
                    params.put("phone", phone);
                    params.put("addressdetail", addressdetail);
                    params.put("userno", userno);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            intent.setClass(this, MyAddressActivity.class);
            startActivity(intent);
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
