package com.jyl.healthytakeout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.jyl.healthytakeout.adapter.GoodsItemAdapter;
import com.jyl.healthytakeout.adapter.SelectAdapter;
import com.jyl.healthytakeout.entity.Address;
import com.jyl.healthytakeout.entity.GoodsItem;
import com.jyl.healthytakeout.entity.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.jyl.healthytakeout.MainActivity.goodsItems;
import static com.jyl.healthytakeout.MainActivity.userinfo;


public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ListView goodsitemlistview;
    public static GoodsItemAdapter adapter;
    public SelectAdapter selectAdapter;
    private TextView tvCount,tvCost,tvSubmit,tvTips,Restaurantname,address,totalcalories;
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private List<GoodsItem>show;
    public static SparseArray<GoodsItem> selectedList;
    private NumberFormat nf;
    private ImageButton ShopcommentBtn;

    private RecyclerView rvSelected;
    private ImageView imgCart;
    private ViewGroup anim_mask_layout;
    private Handler mHanlder;
    private ImageView RestaurantImg;
    private float CaloriesSum=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }//加入返回按钮

        Bundle bundle = getIntent().getExtras();
        List<Restaurant> show=(ArrayList<Restaurant>)bundle.get("show");
        final String key=show.get(0).getRestaurantid();

        RestaurantImg =(ImageView) findViewById(R.id.RestaurantImg);
        Restaurantname = (TextView) findViewById(R.id.Restaurantname);
        address = (TextView) findViewById(R.id.address);

        ShopcommentBtn=(ImageButton)findViewById(R.id.ShopcommentBtn);
        ShopcommentBtn.setOnClickListener(this);

        String imageurl= HttpUtilsHttpURLConnection.BASE_URL+"/image/restaurant/"+key+".png";
        Glide.with(this).load(imageurl).placeholder(R.drawable.ic_restaurant).error(R.drawable.ic_restaurant).into(RestaurantImg);
        Restaurantname.setText((show.get(0).getRestaurantname()));
        address.setText(show.get(0).getRestaurantaddress());

        selectedList = new SparseArray<>();

        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mHanlder = new Handler(getMainLooper());
        imgCart = (ImageView) findViewById(R.id.imgCart);
        anim_mask_layout = (RelativeLayout) findViewById(R.id.containerLayout);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);
        tvCount = (TextView) findViewById(R.id.tvCount);
        tvCost = (TextView) findViewById(R.id.tvCost);
        tvTips = (TextView) findViewById(R.id.tvTips);
        totalcalories = (TextView) findViewById(R.id.totalcalories);
        tvSubmit  = (TextView) findViewById(R.id.tvSubmit);
        goodsitemlistview=(ListView)findViewById(R.id.itemListView);
        goodsItems.clear();
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/FoodQuerybyrestaurantID";
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
                        try {
                            JSONObject json = new JSONObject(key);
                            JSONArray jsonArray = new JSONArray(json.get("food").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String Sfoodno = jsonObject.get("foodno").toString();
                                int foodno=Integer.parseInt(Sfoodno);
                                String category = (String) jsonObject.get("category");
                                String foodname = (String) jsonObject.get("foodname");
                                String Sprice = (String) jsonObject.get("price").toString();
                                float price=Float.parseFloat(Sprice);
                                String Scalories = jsonObject.get("calories").toString();
                                float calories=Float.parseFloat(Scalories);
                                String supplier = (String) jsonObject.get("supplier");
                                String information = (String) jsonObject.get("information");
                                int count=0;

                                GoodsItem s = new GoodsItem(foodno,category,foodname,price,calories,supplier,information,count);
                                goodsItems.add(s);
                            }
                            adapter=new GoodsItemAdapter(ShoppingCartActivity.this,goodsItems);
                            goodsitemlistview.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
        goodsitemlistview.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        final List<Restaurant>[] restaurantshow = new List[]{new ArrayList<Restaurant>()};
        switch (v.getId()){
            case R.id.bottom:
                showBottomSheet();
                break;
            case R.id.ShopcommentBtn:
                Bundle bundle = getIntent().getExtras();
                restaurantshow[0] =(ArrayList<Restaurant>)bundle.get("show");
                intent.setClass(this,ShopCommentActivity.class);
                intent.putExtra("restaurantshow",(Serializable) restaurantshow[0]);
                startActivity(intent);
                break;
            case R.id.clear:
                clearCart();
                break;
            case R.id.tvSubmit:
                if(userinfo.get(0).getGender().equals("男")){
                    if(CaloriesSum>800){
                        new AlertDialog.Builder( this )
                                .setIcon( R.drawable.ic_launcher_foreground)
                                .setTitle( "温馨提示" )
                                .setMessage( "\n您点的餐热量太高了，不利于健康，建议每餐的热量不要超过800kcal\n\n您确定要继续吗？" )
                                .setNegativeButton( "再选选",null )
                                .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent();
                                        Bundle bundle1 = getIntent().getExtras();
                                        restaurantshow[0] =(ArrayList<Restaurant>)bundle1.get("show");
                                        intent.setClass(ShoppingCartActivity.this,SelectAddressActivity.class);
                                        intent.putExtra("restaurantshow",(Serializable) restaurantshow[0]);
                                        startActivity(intent);
                                    }
                                } ).show();
                        break;
                    }
                    else {
                        Bundle bundle1 = getIntent().getExtras();
                        restaurantshow[0] =(ArrayList<Restaurant>)bundle1.get("show");
                        intent.setClass(ShoppingCartActivity.this,SelectAddressActivity.class);
                        intent.putExtra("restaurantshow",(Serializable) restaurantshow[0]);
                        startActivity(intent);
                    }
                }else {
                    if(CaloriesSum>650){
                        new AlertDialog.Builder( this )
                                .setIcon( R.drawable.ic_launcher_foreground)
                                .setTitle( "温馨提示" )
                                .setMessage( "\n您点的餐热量太高了，不利于健康，建议每餐的热量不要超过650kcal\n\n您确定要继续吗？" )
                                .setNegativeButton( "再选选",null )
                                .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent();
                                        Bundle bundle1 = getIntent().getExtras();
                                        restaurantshow[0] =(ArrayList<Restaurant>)bundle1.get("show");
                                        intent.setClass(ShoppingCartActivity.this,SelectAddressActivity.class);
                                        intent.putExtra("restaurantshow",(Serializable) restaurantshow[0]);
                                        startActivity(intent);
                                    }
                                } ).show();
                        break;
                    }
                    else {
                        Bundle bundle1 = getIntent().getExtras();
                        restaurantshow[0] =(ArrayList<Restaurant>)bundle1.get("show");
                        intent.setClass(ShoppingCartActivity.this,SelectAddressActivity.class);
                        intent.putExtra("restaurantshow",(Serializable) restaurantshow[0]);
                        startActivity(intent);
                    }
                }
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        show = new ArrayList<GoodsItem>();
        String foodno=Integer.toString(goodsItems.get(position).getFoodno());

        final AlertDialog foodInfoDialog=new AlertDialog.Builder(this)
                .setTitle("商品详情")
                .setMessage("商品名称："+goodsItems.get(position).foodname
                            +"\n\n商品类别："+goodsItems.get(position).category
                            +"\n\n商品信息："+goodsItems.get(position).information
                            +"\n\n供应商："+goodsItems.get(position).supplier
                )
                .setIcon(R.drawable.ic_logo)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
        foodInfoDialog.show();
    }

    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id){
        GoodsItem temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.count;
    }

    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList){
        int size = selectedList.size();
        int count =0;
        double cost = 0;
        float Tempcalories=0;


        for(int i=0;i<size;i++){
            GoodsItem item = selectedList.valueAt(i);
            count += item.count;
            cost += item.count*item.price;
            Tempcalories += item.count*item.calories;
        }

        if(count<1){
            tvCount.setVisibility(View.GONE);
        }else{
            tvCount.setVisibility(View.VISIBLE);
        }

        tvCount.setText(String.valueOf(count));

        if(cost > 19.99){
            tvTips.setVisibility(View.GONE);
            tvSubmit.setVisibility(View.VISIBLE);
        }else{
            tvSubmit.setVisibility(View.GONE);
            tvTips.setVisibility(View.VISIBLE);
        }

        tvCost.setText(nf.format(cost));

        totalcalories.setText(Float.toString(Tempcalories)+" kcal");
        CaloriesSum=Tempcalories;


        if(adapter!=null && refreshGoodList){
            adapter.notifyDataSetChanged();
        }
        if(selectAdapter!=null){
            selectAdapter.notifyDataSetChanged();
        }

        if(bottomSheetLayout.isSheetShowing() && selectedList.size()<1){
            bottomSheetLayout.dismissSheet();
        }
    }

    //添加商品
    public void add(GoodsItem item,boolean refreshGoodList){
        GoodsItem temp = selectedList.get(item.foodno);
        if(temp==null){
            item.count=1;
            selectedList.append(item.foodno,item);
        }else{
            temp.count++;
        }
        update(refreshGoodList);
    }

    //移除商品
    public void remove(GoodsItem item,boolean refreshGoodList){
        GoodsItem temp = selectedList.get(item.foodno);
        if(temp!=null){
            if(temp.count<2){
                selectedList.remove(item.foodno);
            }else{
                item.count--;
            }
        }
        update(refreshGoodList);
    }

    //清空购物车
    public void clearCart(){
        selectedList.clear();
        CaloriesSum=0;
        update(true);

    }

    private View createBottomSheetView(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet,(ViewGroup) getWindow().getDecorView(),false);
        rvSelected = (RecyclerView) view.findViewById(R.id.selectRecyclerView);
        rvSelected.setLayoutManager(new LinearLayoutManager(this));
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(this);
        selectAdapter = new SelectAdapter(this,selectedList);
        rvSelected.setAdapter(selectAdapter);
        return view;
    }

    private void showBottomSheet(){
        if(bottomSheet==null){
            bottomSheet = createBottomSheetView();
        }
        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if(selectedList.size()!=0){
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }


    public void playAnimation(int[] start_location){
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.button_add);
        setAnim(img,start_location);
    }

    private Animation createAnim(int startX, int startY){
        int[] des = new int[2];
        imgCart.getLocationInWindow(des);

        AnimationSet set = new AnimationSet(false);

        Animation translationX = new TranslateAnimation(0, des[0]-startX, 0, 0);
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1]-startY);
        translationY.setInterpolator(new AccelerateInterpolator());
        Animation alpha = new AlphaAnimation(1,0.5f);
        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setDuration(500);

        return set;
    }

    private void addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {

        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y-loc[1]);
        vg.addView(view);
    }
    private void setAnim(final View v, int[] start_location) {

        addViewToAnimLayout(anim_mask_layout, v, start_location);
        Animation set = createAnim(start_location[0],start_location[1]);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_mask_layout.removeView(v);
                    }
                },100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(set);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }//加入返回按钮
}
