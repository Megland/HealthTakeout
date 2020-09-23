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
import static com.jyl.healthytakeout.MyAddressActivity.tempaddress;

public class AddressEditActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editcontactperson;
    private EditText editcpinputphone;
    private EditText editaddressdetail;
    private RadioGroup editCpgender;
    private RadioButton editrbcpmale;
    private RadioButton editrbcpfemale;
    private Button btneditaddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);


        /*Toast.makeText(this, Integer.toString(tempaddress.get(0).getAddressno()), Toast.LENGTH_SHORT).show();*/

        editcontactperson=(EditText)findViewById(R.id.edit_contact_person);
        editcpinputphone=(EditText)findViewById(R.id.edit_cp_input_phone);
        editaddressdetail=(EditText)findViewById(R.id.edit_addressdetail);

        editCpgender=(RadioGroup)findViewById(R.id.edit_Cp_gender);
        editrbcpmale=(RadioButton)findViewById(R.id.edit_rb_cp_male);
        editrbcpfemale=(RadioButton)findViewById(R.id.edit_rb_cp_female);

        btneditaddress=(Button)findViewById(R.id.btn_edit_address);
        btneditaddress.setOnClickListener(this);

        editcontactperson.setText(tempaddress.get(0).getContactperson());
        editcpinputphone.setText(tempaddress.get(0).getPhone());
        editaddressdetail.setText(tempaddress.get(0).getAddressdetail());

        if(tempaddress.get(0).getGender().equals("0")){
            editrbcpmale.setChecked(true);
        }else{
            editrbcpfemale.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_edit_address){
            final String addressno = Integer.toString(tempaddress.get(0).getAddressno());
            final String contactperson = editcontactperson.getText().toString();
            final String gender = getRadio(editCpgender);
            final String phone = editcpinputphone.getText().toString();
            final String addressdetail = editaddressdetail.getText().toString();
            final String userno = Integer.toString(userinfo.get(0).getUserno());

            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/AddressInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("addressno", addressno);
                    params.put("contactperson", contactperson);
                    params.put("gender", gender);
                    params.put("phone", phone);
                    params.put("addressdetail", addressdetail);
                    params.put("userno", userno);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent();
            intent.setClass(this, MyAddressActivity.class);
            startActivity(intent);
        }
        tempaddress.clear();
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
