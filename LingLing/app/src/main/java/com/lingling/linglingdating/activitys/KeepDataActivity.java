package com.lingling.linglingdating.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.adapter2.KeepAdapter;
import com.lingling.linglingdating.bean.Per;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

/**
 * mqq
 * Created by 令令 on 2017/7/24.
 * 这是赴约的界面
 */
public class KeepDataActivity extends SimpleTopbarActivity {
    private static Class<?> rightFuncArray1[] = {CommonBackTopBtnFuncRight.class};
    @Bind(R.id.txt_uninterest_keep_data)
    TextView txtUninterestKeepData;
    @Bind(R.id.listView_keep_data)
    ListView listViewKeepData;
    private List<Per> data=null;
    private KeepAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keep_data_layout);
        ButterKnife.bind(this);
        initDatas();
        initListener();

    }

    private void initListener() {
        txtUninterestKeepData.setOnClickListener(this);
    }

    //设置数据
    private void initDatas() {
        data=new ArrayList();
        for (int i = 0; i <10 ; i++) {
            Per p=new Per();
            p.im=R.mipmap.tou_xiang;
            p.name="小宝";
            p.time="周六 下午7：00";
            data.add(p);
        }

        adapter = new KeepAdapter(KeepDataActivity.this,data,R.layout.keep_data_item,0);
        listViewKeepData.setAdapter(adapter);

    }

    //监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_uninterest_keep_data:
               //跳转到不感兴趣的界面
                startActivity(new Intent(this,UnInterestActivity.class));
                break;
        }
        super.onClick(v);
    }

    @Override
    protected Object getTopbarTitle() {
        return "赴约的人";
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
    }

    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return rightFuncArray1;
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
