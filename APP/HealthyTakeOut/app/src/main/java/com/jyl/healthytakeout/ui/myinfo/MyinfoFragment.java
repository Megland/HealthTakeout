package com.jyl.healthytakeout.ui.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.jyl.healthytakeout.EditUserInfoActivity;
import com.jyl.healthytakeout.FeedbackActivity;
import com.jyl.healthytakeout.LoginActivity;
import com.jyl.healthytakeout.MainActivity;
import com.jyl.healthytakeout.MyAddressActivity;
import com.jyl.healthytakeout.MyOrderActivity;
import com.jyl.healthytakeout.widget.section.SectionTextItemView;
import com.jyl.healthytakeout.R;

import java.text.DecimalFormat;

import static com.jyl.healthytakeout.MainActivity.userinfo;

public class MyinfoFragment extends Fragment implements View.OnClickListener {

    private MyinfoViewModel myinfoViewModel;
    private TextView mUsername;
    private TextView kcalnotification;
    private ImageView mUserAvatar;

    private SectionTextItemView mManageAddressBtn;
    private SectionTextItemView mManageMyInfo;
    private SectionTextItemView mManageOrderBtn;
    private SectionTextItemView mManageEvaluate;
    private SectionTextItemView mFeedback;
    private SectionTextItemView mLogoutBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        myinfoViewModel = new ViewModelProvider(this).get(MyinfoViewModel.class);//原viewModel被弃用了，这是新的方法
        View root = inflater.inflate(R.layout.fragment_myinfo, container, false);

        /*Toast.makeText(getContext(), userinfo.get(0).getUsername()+" "+userinfo.get(0).getAge()+" "+userinfo.get(0).getGender(), Toast.LENGTH_SHORT).show();*/
        mManageAddressBtn=(SectionTextItemView)root.findViewById(R.id.btn_my_address);
        mManageMyInfo=(SectionTextItemView)root.findViewById(R.id.btn_my_info);
        mManageOrderBtn=(SectionTextItemView)root.findViewById(R.id.btn_my_order);

        mFeedback=(SectionTextItemView)root.findViewById(R.id.btn_feedback);
        mLogoutBtn=(SectionTextItemView)root.findViewById(R.id.btn_logout);
        mUsername=(TextView)root.findViewById(R.id.username);
        kcalnotification=(TextView)root.findViewById(R.id.kcalnotification);
        mUserAvatar=(ImageView)root.findViewById(R.id.userAvatar);

        mUsername.setText(userinfo.get(0).getUsername());

        if(userinfo.get(0).getGender().equals("女")){
            mUserAvatar.setImageResource(R.drawable.ic_avatar_girl);
        }
        float h=userinfo.get(0).getHeight()/100;
        DecimalFormat df = new DecimalFormat("#.00");
        float BMI=userinfo.get(0).getWeight()/(h*h);


        if(BMI<=18.5){
            kcalnotification.setText("BMI指数为"+df.format(BMI)+"\n您太瘦了，要多吃点哦！");
        }else if(BMI<=24.9){
            kcalnotification.setText("BMI指数为"+df.format(BMI)+"\n您的体重正常，继续保持！");
        }else {
            kcalnotification.setText("BMI指数为"+df.format(BMI)+"\n您已超重，要注意饮食！");
        }


        mManageAddressBtn.setOnClickListener(this);
        mManageMyInfo.setOnClickListener(this);
        mManageOrderBtn.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
        mLogoutBtn.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_my_address:
                intent.setClass(getContext(), MyAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_my_info:
                intent.setClass(getContext(), EditUserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_my_order:
                intent.setClass(getContext(), MyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_feedback:
                intent.setClass(getContext(), FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_logout:
                userinfo.clear();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}