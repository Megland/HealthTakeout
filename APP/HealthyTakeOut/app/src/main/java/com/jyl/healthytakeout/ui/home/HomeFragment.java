package com.jyl.healthytakeout.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jyl.healthytakeout.HttpUtilsHttpURLConnection;
import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.ShoppingCartActivity;
import com.jyl.healthytakeout.adapter.RestaurantAdapter;
import com.jyl.healthytakeout.entity.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.jyl.healthytakeout.MainActivity.restaurants;

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private HomeViewModel homeViewModel;
    private ListView listView;
    private Button add;
    public static RestaurantAdapter adapter;
    private ImageButton search;
    private EditText find1;
    public static List<Restaurant> find;
    private List<Restaurant>show;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        super.onCreate(savedInstanceState);

        listView = (ListView)root.findViewById(R.id.listView);
        find1=(EditText)root.findViewById(R.id.find);
        search=(ImageButton)root.findViewById(R.id.search);
        search.setOnClickListener(this);
        restaurants.clear();

        if (find!=null) {
            find.clear();
        }
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/RestaurantQuery";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key", "");
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
                            JSONArray jsonArray = new JSONArray(json.get("restaurant").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String restaurantid = jsonObject.get("restaurantid").toString();
                                String restaurantname = (String) jsonObject.get("restaurantname");
                                String introduction = (String) jsonObject.get("introduction");
                                String restaurantaddress = (String) jsonObject.get("restaurantaddress");
                                Restaurant s = new Restaurant(restaurantid, restaurantname, introduction, restaurantaddress);
                                restaurants.add(s);
                            }
                            adapter = new RestaurantAdapter(getActivity(), restaurants);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
        listView.setOnItemClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();

        if(v.getId()==R.id.search){
            final String key=find1.getText().toString();
            find=new ArrayList<Restaurant>();
            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/RestaurantQuery";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("flag", "1");
                    params.put("key",key);
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
                            restaurants.clear();
                            try {
                                JSONObject json = new JSONObject(key);
                                JSONArray jsonArray = new JSONArray(json.get("restaurant").toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                    String restaurantid = jsonObject.get("restaurantid").toString();
                                    String restaurantname = (String) jsonObject.get("restaurantname");
                                    String introduction = (String) jsonObject.get("introduction");
                                    String restaurantaddress = (String) jsonObject.get("restaurantaddress");
                                    Restaurant s = new Restaurant(restaurantid, restaurantname, introduction, restaurantaddress);
                                    restaurants.add(s);
                                }
                                adapter = new RestaurantAdapter(getActivity(), restaurants);
                                listView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                };
            }.start();
            listView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        show=new ArrayList<Restaurant>();
        String restaurantid=restaurants.get(position).getRestaurantid();
        String restaurantname=restaurants.get(position).getRestaurantname();
        String introduction=restaurants.get(position).getIntroduction();
        String restaurantaddress=restaurants.get(position).getRestaurantaddress();
        Restaurant restaurant=new Restaurant(restaurantid, restaurantname, introduction, restaurantaddress);
        show.add(restaurant);
        /*Toast.makeText(getActivity(),restaurantid+" "+restaurantname+" "+restaurantaddress+" "+introduction, Toast.LENGTH_SHORT).show();*/
        Intent intent=new Intent();
        intent.setClass(getContext(), ShoppingCartActivity.class);
        intent.putExtra("show",(Serializable)show);
        startActivity(intent);
    }
}