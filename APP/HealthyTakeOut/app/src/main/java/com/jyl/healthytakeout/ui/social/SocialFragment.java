package com.jyl.healthytakeout.ui.social;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jyl.healthytakeout.FansActivity;
import com.jyl.healthytakeout.FindUserActivity;
import com.jyl.healthytakeout.FocusActivity;
import com.jyl.healthytakeout.MessageActivity;
import com.jyl.healthytakeout.MyAddressActivity;
import com.jyl.healthytakeout.R;
import com.jyl.healthytakeout.TrendActivity;
import com.jyl.healthytakeout.widget.section.SectionTextItemView;

public class SocialFragment extends Fragment implements View.OnClickListener {

    private SocialViewModel socialViewModel;


    private SectionTextItemView mTrendBtn;
    private SectionTextItemView mMessageInfoBtn;
    private SectionTextItemView mMyFocusBtn;
    private SectionTextItemView mMyFansBtn;
    private SectionTextItemView mFindUserBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        socialViewModel =
                ViewModelProviders.of(this).get(SocialViewModel.class);
        View root = inflater.inflate(R.layout.fragment_social, container, false);

        mTrendBtn=(SectionTextItemView)root.findViewById(R.id.btn_trend);
        mMessageInfoBtn=(SectionTextItemView)root.findViewById(R.id.btn_message);
        mMyFocusBtn=(SectionTextItemView)root.findViewById(R.id.btn_my_focus);
        mMyFansBtn=(SectionTextItemView)root.findViewById(R.id.btn_my_fans);
        mFindUserBtn=(SectionTextItemView)root.findViewById(R.id.btn_find_user);

        mTrendBtn.setOnClickListener(this);
        mMessageInfoBtn.setOnClickListener(this);
        mMyFocusBtn.setOnClickListener(this);
        mMyFansBtn.setOnClickListener(this);
        mFindUserBtn.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_trend:
                intent.setClass(getContext(), TrendActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_message:
                intent.setClass(getContext(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_my_focus:
                intent.setClass(getContext(), FocusActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_my_fans:
                intent.setClass(getContext(), FansActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_find_user:
                intent.setClass(getContext(), FindUserActivity.class);
                startActivity(intent);
                break;
        }
    }
}