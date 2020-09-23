package com.jyl.healthytakeout;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jyl.healthytakeout.entity.Address;
import com.jyl.healthytakeout.entity.Restaurant;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jyl.healthytakeout.ShoppingCartActivity.selectedList;
import static com.jyl.healthytakeout.MainActivity.userinfo;

public class CreateOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView OrderAddressName;
    private TextView OrderAddressGender;
    private TextView OrderAddressMoblie;
    private TextView OrderAddress;
    private TextView tvOrderdetail;
    private TextView TotalOrderPrice;
    private TextView SelectAddressTV;
    private Button BtnCreateOrder;
    private NumberFormat nf;
    private String orderdetails;
    private float Totalprices=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        OrderAddressName = (TextView) findViewById(R.id.order_address_name);
        OrderAddressGender = (TextView) findViewById(R.id.order_address_gender);
        OrderAddressMoblie = (TextView) findViewById(R.id.order_address_mobile);
        OrderAddress = (TextView) findViewById(R.id.order_address);
        tvOrderdetail = (TextView) findViewById(R.id.tvOrderdetail);
        TotalOrderPrice = (TextView) findViewById(R.id.TotalOrderPrice);
        BtnCreateOrder =(Button)findViewById(R.id.btn_create_order);

        BtnCreateOrder.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        List<Address> show=(ArrayList<Address>)bundle.get("show");
        int addressno=show.get(0).getAddressno();
        String contactperson=show.get(0).getContactperson();
        String gender=show.get(0).getGender();
        String phone=show.get(0).getPhone();
        String addressdetail=show.get(0).getAddressdetail();
        int userno=show.get(0).getUserno();

        OrderAddressName.setText(contactperson);
        OrderAddressGender.setText("先生");
        if(gender.equals("1")){
            OrderAddressGender.setText("女士");
        }
        OrderAddressMoblie.setText(phone);
        OrderAddress.setText(addressdetail);

        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);

        String TempOrderDetail="\n";
        orderdetails="";


        for(int i=0;i<selectedList.size();i++){
            float price=selectedList.valueAt(i).price*selectedList.valueAt(i).count;
            TempOrderDetail=TempOrderDetail+
                    "   "+selectedList.valueAt(i).foodname+
                    "        数量："+selectedList.valueAt(i).count+
                    "        价格："+nf.format(price)+"\n";
            Totalprices =Totalprices + price;

            orderdetails=orderdetails+selectedList.valueAt(i).foodname+"*"+selectedList.valueAt(i).count+"、";
        }
        tvOrderdetail.setText(TempOrderDetail);
        TotalOrderPrice.setText("总价："+nf.format(Totalprices));
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_create_order){
            Bundle bundle = getIntent().getExtras();
            List<Restaurant> restaurantshow=(ArrayList<Restaurant>)bundle.get("restaurantshow");
            List<Address> show=(ArrayList<Address>)bundle.get("show");
            final String userno = Integer.toString(userinfo.get(0).getUserno());
            final String username = userinfo.get(0).getUsername();
            final String restaurantid = restaurantshow.get(0).getRestaurantid();
            final String restaurantname =restaurantshow.get(0).getRestaurantname();
            final String orderdetail = orderdetails;
            final String Totalprice=Float.toString(Totalprices);
            final String starttime = "";
            final String orderstatus = "已下单，正在等待配送";
            final String addressno =Integer.toString(show.get(0).getAddressno());
            final String orderaddress =show.get(0).getContactperson()+" "+show.get(0).getPhone()+" "+show.get(0).getAddressdetail();
            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/OrderInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username);
                    params.put("userno", userno);
                    params.put("restaurantid", restaurantid);
                    params.put("restaurantname", restaurantname);
                    params.put("orderdetails", orderdetail);
                    params.put("totalprice", Totalprice);
                    params.put("starttime", starttime);
                    params.put("orderstatus", orderstatus);
                    params.put("addressno", addressno);
                    params.put("orderaddress", orderaddress);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "下单成功，正在等待配送", Toast.LENGTH_SHORT).show();
        }
    }
}
