package com.lingling.linglingdating.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * mqq
 * Created by 令令 on 2017/7/19.
 * 这是设置的界面
 */
public class SetActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.image_back_include_more)
    ImageView imageBackIncludeMore;
    @Bind(R.id.txt_name_include_more)
    TextView txtNameIncludeMore;
    @Bind(R.id.txt_dw_include_more)
    TextView txtDwIncludeMore;
    @Bind(R.id.image_js_set)
    ImageView imageJsSet;
    @Bind(R.id.image_zd_set)
    ImageView imageZdSet;
    @Bind(R.id.image_ls_set)
    ImageView imageLsSet;
    private boolean flag_js=false;
    private boolean flag_zd=false;
    private boolean flag_ls=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_layout);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
       txtNameIncludeMore.setText("设置");
        imageJsSet.setOnClickListener(this);
        imageZdSet.setOnClickListener(this);
        imageLsSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back_include_more:finish();
                break;
            case R.id.image_js_set:
                flag_js=!flag_js;
                imageJsSet.setSelected(flag_js);
                break;
            case R.id.image_zd_set:
                flag_zd=!flag_zd;
                imageZdSet.setSelected(flag_zd);
                break;
            case R.id.image_ls_set:
                flag_ls=!flag_ls;
                imageLsSet.setSelected(flag_ls);
                break;


        }
    }
}
