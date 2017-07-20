package com.lingling.linglingdating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.adapter.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 令令 on 2017/7/13.
 */
public class MainActivity extends BaseActivity {
    private TextView text_jump;
    private TextView start_jump;
    private ArrayList<ImageView> list_im;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        list_im=new ArrayList<>();
        int[] in=new int[]{R.mipmap.lead_one,R.mipmap.lead_two,R.mipmap.lead_three};
        viewPager = (ViewPager) findViewById(R.id.viewPager_lead_main);
        TextView  text_jump = (TextView) findViewById(R.id.text_jump_m);
       text_jump.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, StartMainActivity.class));
           }
       });

        for (int i = 0; i <3 ; i++) {
            ImageView im=new ImageView(this);
            im.setImageResource(in[i]);
            im.setScaleType(ImageView.ScaleType.FIT_XY);
            list_im.add(im);
        }

        viewPager.setAdapter(new ViewPagerAdapter(this,list_im));
        //监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position==2){
                    TextView  start_jump = (TextView) findViewById(R.id.text_start_m);
                    start_jump.setVisibility(View.VISIBLE);
                      start_jump.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, StartMainActivity.class));
            }});
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}
