package com.lingling.linglingdating.activity;
/**
 * 这是启动界面
 * by Ling
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lingling.linglingdating.R;

public class StartMainActivity extends BaseActivity {

    private ImageView image_boy_start;
    private ImageView image_girl_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ling_start);
        initViews();
   }

    private void initViews() {
        image_boy_start = (ImageView) findViewById(R.id.image_boy_start);
        image_girl_start = (ImageView) findViewById(R.id.image_girl_start);

        image_boy_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartMainActivity.this,LogActivity.class));
            }
        });
        image_girl_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartMainActivity.this,LogActivity.class));
            }
        });

    }
}
