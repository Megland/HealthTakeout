package com.jyl.healthytakeout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jyl.healthytakeout.adapter.AddressAdapter;
import com.jyl.healthytakeout.entity.Address;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.jyl.healthytakeout.MainActivity.userAddressinfo;
import static com.jyl.healthytakeout.MainActivity.userinfo;

public class MyAddressActivity extends AppCompatActivity implements AddressAdapter.InnerItemOnClickListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView listView;
    public static AddressAdapter adapter;
    public static ArrayList<Address> tempaddress = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        listView = findViewById(R.id.addresslistview);
        adapter = new AddressAdapter(MyAddressActivity.this, userAddressinfo);
        listView.setAdapter(adapter);
        adapter.setOnInnerItemOnClickListener(MyAddressActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userAddressinfo.clear();
        final String userNo=Integer.toString(userinfo.get(0).getUserno());
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/AddressQuery";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key",userNo);
                String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                ///返回JSON
                Message msg = new Message();
                msg.what = 0x11;
                Bundle data = new Bundle();
                data.putString("result", result);
                System.out.println(result);
                msg.setData(data);
                handler.sendMessage(msg);
            }
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x11) {
                        Bundle data = msg.getData();
                        String key = data.getString("result");//得到json返回的json
                        try {
                            JSONObject json = new JSONObject(key);
                            JSONArray jsonArray = new JSONArray(json.get("address").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String Saddressno = jsonObject.get("addressno").toString();
                                int addressno=Integer.parseInt(Saddressno);
                                String contactperson = (String) jsonObject.get("contactperson");
                                String gender = (String) jsonObject.get("gender");
                                String phone = (String) jsonObject.get("phone");
                                String addressdetail = (String) jsonObject.get("addressdetail");
                                String Suserno = (String) jsonObject.get("userno").toString();
                                int userno=Integer.parseInt(Suserno);
                                Address s = new Address(addressno,contactperson,gender,phone,addressdetail,userno);
                                userAddressinfo.add(s);
                            }
                            adapter = new AddressAdapter(MyAddressActivity.this, userAddressinfo);
                            listView.setAdapter(adapter);
                            adapter.setOnInnerItemOnClickListener(MyAddressActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addaddress){
            Intent intent=new Intent();
            intent.setClass(MyAddressActivity.this,AddAddressActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/*        int addressno=userAddressinfo.get(position).getAddressno();
        String contactperson=userAddressinfo.get(position).getContactperson();
        String gender=userAddressinfo.get(position).getGender();
        String phone=userAddressinfo.get(position).getPhone();
        String addressdetail=userAddressinfo.get(position).getAddressdetail();
        int userno=userAddressinfo.get(position).getUserno();

        Toast.makeText(this, Integer.toString(addressno), Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void itemClick(View view) {
        switch (view.getId()) {
            case R.id.btn_inneritem_edit_address:
                tempaddress.clear();
                int p=(Integer) view.getTag();
                int addressno=userAddressinfo.get(p).getAddressno();
                String contactperson=userAddressinfo.get(p).getContactperson();
                String gender=userAddressinfo.get(p).getGender();
                String phone=userAddressinfo.get(p).getPhone();
                String addressdetail=userAddressinfo.get(p).getAddressdetail();
                int userno=userAddressinfo.get(p).getUserno();
                Bundle args = new Bundle();

                Address s=new Address(addressno,contactperson,gender,phone,addressdetail,userno);
                tempaddress.add(s);

                Intent intent=new Intent();
                intent.setClass(this,AddressEditActivity.class);
/*                intent.putExtra("mylist", tempaddress);*/
                startActivity(intent);
                Toast.makeText(this, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete_address:
                final int k=(Integer)view.getTag();
                new AlertDialog.Builder( this )
                        .setIcon( R.drawable.ic_delete)
                        .setTitle( "删除" )
                        .setMessage( "\n确定删除此地址?" )
                        .setNegativeButton( "取消",null )
                        .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String no=Integer.toString(userAddressinfo.get(k).getAddressno());
                                new Thread() {
                                    @Override
                                    public void run() {
                                        String url = HttpUtilsHttpURLConnection.BASE_URL + "/AddressDelServlet";
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("addressno", no);
                                        String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                                        System.out.println(result);
                                    }
                                }.start();
                                userAddressinfo.remove(k);
                                Toast.makeText(MyAddressActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                            }
                        } ).show();
                break;
        }
    }

    public void sentI(Intent intent,int p){
        intent.putExtra("p",p);
    }
}
