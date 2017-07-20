package com.lingling.linglingdating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.activitys.ChangeNameActivity;
import com.lingling.linglingdating.activitys.SetActivity;
import com.lingling.linglingdating.imageUtils.CustomConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * mqq
 * Created by 令令 on 2017/7/19.
 * 这是更多的界面
 */
public class MoreActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.image_back_include_more)
    ImageView imageBackIncludeMore;
    @Bind(R.id.txt_name_include_more)
    TextView txtNameIncludeMore;
    @Bind(R.id.txt_dw_include_more)
    TextView txtDwIncludeMore;
    @Bind(R.id.txt_name_me_more)
    TextView txtNameMeMore;
    @Bind(R.id.image_name_me_more)
    ImageView imageNameMeMore;
    @Bind(R.id.txt_time_me_more)
    TextView txtTimeMeMore;
    @Bind(R.id.image_time_me_more)
    ImageView imageTimeMeMore;
    @Bind(R.id.txt_content_me_more)
    TextView txtContentMeMore;
    @Bind(R.id.image_content_me_more)
    ImageView imageContentMeMore;
    @Bind(R.id.image_mzh_me_more)
    ImageView imageMzhMeMore;
    @Bind(R.id.image_mrw_me_more)
    ImageView imageMrwMeMore;
    @Bind(R.id.txt_set_me_more)
    TextView txtSetMeMore;
    @Bind(R.id.image_sz_me_more)
    ImageView imageSzMeMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_more_layout);
        ButterKnife.bind(this);
        initDatas();
        initListener();
    }

    private void initListener() {
        imageSzMeMore.setOnClickListener(this);
        imageContentMeMore.setOnClickListener(this);
        imageMrwMeMore.setOnClickListener(this);
        imageNameMeMore.setOnClickListener(this);
        imageTimeMeMore.setOnClickListener(this);
        imageMzhMeMore.setOnClickListener(this);

    }

    private void initDatas() {
        txtNameIncludeMore.setText("更多");
        imageBackIncludeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //设置
            case R.id.image_sz_me_more:
                startActivity(new Intent(this, SetActivity.class));
                break;
            //修改名称
            case R.id.image_name_me_more:
                Intent intent = new Intent(this, ChangeNameActivity.class);
                startActivityForResult(intent, CustomConstants.CHANGE_NAME_CODE);

                break;
            //修改时间
            case R.id.image_time_me_more:
                startActivity(new Intent(this, SetActivity.class));
                break;
            //我的任务
            case R.id.image_mrw_me_more:
                startActivity(new Intent(this, SetActivity.class));
                break;
            //我的账户
            case R.id.image_mzh_me_more:
                startActivity(new Intent(this, SetActivity.class));
                break;
            //内容
            case R.id.image_content_me_more:
                startActivity(new Intent(this, SetActivity.class));
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           if(data!=null){
            if(requestCode==CustomConstants.CHANGE_NAME_CODE&&resultCode==CustomConstants.CHANGE_NAME_BACK){
                //设置名称
                txtNameMeMore.setText(data.getStringExtra("name"));
            }
         }
     }
}
