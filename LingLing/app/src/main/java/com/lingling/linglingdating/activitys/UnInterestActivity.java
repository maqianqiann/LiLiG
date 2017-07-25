package com.lingling.linglingdating.activitys;

import android.os.Bundle;
import android.widget.ListView;

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
 * 这是不感兴趣的界面
 */
public class UnInterestActivity extends SimpleTopbarActivity {
    @Bind(R.id.listView_no_interest)
    ListView listViewNoInterest;
    private KeepAdapter adapter;
    private List<Per> data;
    private int anInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_interest_layout);
        ButterKnife.bind(this);
        initDatas();
    }
    private void initDatas() {
        data=new ArrayList();
        for (int i = 0; i <10 ; i++) {
            Per p=new Per();
            p.im=R.mipmap.tou_xiang;
            p.name="小宝";
            p.time="周六 下午7：00";
            data.add(p);
        }
        if(adapter==null){
             adapter=new KeepAdapter(this,data,R.layout.keep_data_item,1);
             listViewNoInterest.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    protected Object getTopbarTitle() {
        return "不感兴趣的";
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
