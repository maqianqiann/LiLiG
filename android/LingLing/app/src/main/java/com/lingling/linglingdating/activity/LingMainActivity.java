package com.lingling.linglingdating.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.adapter.LingViewPager;
import com.lingling.linglingdating.fragment.DataFragment;
import com.lingling.linglingdating.fragment.LingFragment;
import com.lingling.linglingdating.fragment.MeFragment;
import com.lingling.linglingdating.fragment.ZuFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 令令 on 2017/7/13.
 * 这是主界面
 */
public class LingMainActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.viewPager_main)
    ViewPager viewPagerMain;
    @Bind(R.id.ling_ling_rd)
    RadioButton lingLingRd;
    @Bind(R.id.yue_ling_rd)
    RadioButton yueLingRd;
    @Bind(R.id.zb_ling_rd)
    RadioButton zbLingRd;
    @Bind(R.id.me_ling_rd)
    RadioButton meLingRd;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;

    private ArrayList<Fragment> list_frag;
    private ArrayList<RadioButton> list_rd;
    private String Tag="LingMainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ling_main);
        ButterKnife.bind(this);
        //创建数据
        initDatas();

    }

    private void initDatas() {
        list_frag=new ArrayList<>();
        list_rd=new ArrayList<>();
        //添加fragemnt
        LingFragment lf=new LingFragment();
        DataFragment df=new DataFragment();
        ZuFragment zf=new ZuFragment();
        MeFragment mf=new MeFragment();
        list_frag.add(lf);
        list_frag.add(df);
        list_frag.add(zf);
        list_frag.add(mf);
        lingLingRd.setSelected(true);
        list_rd.add(lingLingRd);
        list_rd.add(yueLingRd);
        list_rd.add(zbLingRd);
        list_rd.add(meLingRd);
        viewPagerMain.setAdapter(new LingViewPager(getSupportFragmentManager(),list_frag));

        //切换按钮
        chageMenu();
        viewPagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <list_rd.size() ; i++) {
                    if (i==position){
                        list_rd.get(i).setSelected(true);
                    }else{
                        list_rd.get(i).setSelected(false);
                 }}}
            @Override
            public void onPageScrollStateChanged(int state) {
            }});
    }
    private void chageMenu() {
        lingLingRd.setOnClickListener(this);
        yueLingRd.setOnClickListener(this);
        zbLingRd.setOnClickListener(this);
        meLingRd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

             switch (v.getId()){
                 case  R.id.ling_ling_rd:
                     lingLingRd.setSelected(true);
                     zbLingRd.setSelected(false);
                     yueLingRd.setSelected(false);
                     meLingRd.setSelected(false);
                     viewPagerMain.setCurrentItem(0);

                     break;
                 case  R.id.yue_ling_rd:
                     lingLingRd.setSelected(false);
                     zbLingRd.setSelected(false);
                     yueLingRd.setSelected(true);
                     meLingRd.setSelected(false);
                     viewPagerMain.setCurrentItem(1);

                     break;
                 case  R.id.zb_ling_rd:
                     lingLingRd.setSelected(false);
                     zbLingRd.setSelected(true);
                     yueLingRd.setSelected(false);
                     meLingRd.setSelected(false);
                     viewPagerMain.setCurrentItem(2);

                     break;
                 case  R.id.me_ling_rd:
                     viewPagerMain.setCurrentItem(3);
                     lingLingRd.setSelected(false);
                     zbLingRd.setSelected(false);
                     yueLingRd.setSelected(false);
                     meLingRd.setSelected(true);

                     break;

             }


    }



}
