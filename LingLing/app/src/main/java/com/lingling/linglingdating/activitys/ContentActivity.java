package com.lingling.linglingdating.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.adapter.ImageAdapter;
import com.lingling.linglingdating.adapter2.ContentAdapter;
import com.lingling.linglingdating.view.MyListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

/**
 * mqq
 * Created by 令令 on 2017/7/20.
 * 这是约么详情界面
 */
public class ContentActivity extends SimpleTopbarActivity {

    @Bind(R.id.right_func)
    LinearLayout rightFunc;
    @Bind(R.id.txt_tou_data_content)
    ImageView txtTouDataContent;
    @Bind(R.id.txt_name_data_content)
    TextView txtNameDataContent;
    @Bind(R.id.txt_time_data_content)
    TextView txtTimeDataContent;
    @Bind(R.id.txt_price_data_content)
    TextView txtPriceDataContent;
    @Bind(R.id.im_s_data_content)
    ImageView imSDataContent;
    @Bind(R.id.txt_desc_data_content)
    TextView txtDescDataContent;
    @Bind(R.id.GridView_data_content)
    GridView GridViewDataContent;
    @Bind(R.id.txt_data_content)
    TextView txtDataContent;
    @Bind(R.id.edit_data_content)
    EditText editDataContent;
    @Bind(R.id.im_bq_data_content)
    ImageView imBqDataContent;
    @Bind(R.id.im_cream_data_content)
    ImageView imCreamDataContent;
    @Bind(R.id.listview_data_content)
     MyListView listviewDataContent;
    @Bind(R.id.but_xiu_data_content)
    Button butXiuDataContent;
    @Bind(R.id.txt_fb_data_content)
    TextView txtFbDataContent;
    private String content;
    private ArrayList<String> list;
    //定义一个标识用来判断是本人登陆还是其他用户进行登陆
    private boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_content_layout);
        ButterKnife.bind(this);
        butXiuDataContent.setText("查看赴约的人");
        initDatas();
        initListener();


    }

    private void initDatas() {
        if(flag){
        }else{
            butXiuDataContent.setText("我要赴约");
        }
        list=new ArrayList<>();
        GridViewDataContent.setAdapter(new ImageAdapter(this));

    }

    private void initListener() {
        txtFbDataContent.setOnClickListener(this);
        butXiuDataContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_fb_data_content:
                //获得输入的发表的内容
                content = editDataContent.getText().toString();
                if(content!=null){
                    list.add(content);
                   //显示listView的列表
                    listviewDataContent.setVisibility(View.VISIBLE);
                    listviewDataContent.setAdapter(new ContentAdapter(this,list));
                }else{
                    Toast.makeText(ContentActivity.this, "评论不能为空哦", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.but_xiu_data_content:
                  if(flag){
                      //进行查看赴约的人
                      startActivity(new Intent(this,KeepDataActivity.class));
                  }else{
                      butXiuDataContent.setText("我要赴约");
                  // startActivity(new Intent(this,KeepDataActivity.class));
                  }
                break;
        }
        super.onClick(v);
    }

    @Override
    protected Object getTopbarTitle() {
        return "详情";
    }


    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {

    }

    @Override
    public void onCancelResult() {

    }

    @Override
    public void onErrorResult(int errorCode, IOException errorExcep) {

    }

    @Override
    public void onParseErrorResult(int errorCode) {

    }

    @Override
    public void onFinishResult() {

    }
}
