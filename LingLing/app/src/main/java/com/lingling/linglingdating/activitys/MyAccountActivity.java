package com.lingling.linglingdating.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.fragments.InComeFragment;
import com.lingling.linglingdating.fragments.PaymentFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.BaseActivity;

/**
 * Created by 令令 on 2017/7/20.
 * 这是我的账户的界面
 */
public class MyAccountActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.image_back_me_zh)
    ImageView imageBackMeZh;
    @Bind(R.id.txt_tx_me_zh)
    TextView txtTxMeZh;
    @Bind(R.id.txt_yue_me_zh)
    TextView txtYueMeZh;
    @Bind(R.id.but_sr_me_zh)
    Button butSrMeZh;
    @Bind(R.id.but_zc_me_zh)
    Button butZcMeZh;
    @Bind(R.id.frag_me_zh)
    FrameLayout fragMeZh;
    private ArrayList<Fragment> list;
    private InComeFragment income;
    private PaymentFragment pay;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_zh_layout);
        ButterKnife.bind(this);
         initDatas();
         initListener();
    }
    private void initListener() {
        imageBackMeZh.setOnClickListener(this);
        butSrMeZh.setOnClickListener(this);
        butZcMeZh.setOnClickListener(this);
        txtTxMeZh.setOnClickListener(this);
    }
    private void initDatas() {
        //添加fragment
        list=new ArrayList<>();
        pay = new PaymentFragment();
        income = new InComeFragment();
        list.add(income);
        list.add(pay);
        //建立事务管理者
        manager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = manager.beginTransaction();
        beginTransaction.add(R.id.frag_me_zh, income);
        beginTransaction.add(R.id.frag_me_zh, pay);
        beginTransaction.show(income);
        beginTransaction.hide(pay);
        beginTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back_me_zh:finish();break;
            //提现的操作
            case R.id.txt_tx_me_zh:
                startActivity(new Intent(this,TakeMoneyActivity.class));
                break;
            //收入的操作
            case R.id.but_sr_me_zh:
                showAndHide(income,pay);
                break;
             //支出的操作
            case R.id.but_zc_me_zh:
                showAndHide(pay,income);
                break;
        }
    }
    private void showAndHide(Fragment f1,Fragment f2){
        FragmentTransaction beg = manager.beginTransaction();
        beg.show(f1);
        beg.hide(f2);
        beg.commit();
    }
}
