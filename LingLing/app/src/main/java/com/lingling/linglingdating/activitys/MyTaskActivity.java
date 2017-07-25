package com.lingling.linglingdating.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.adapter.ListViewDataAdapter;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

/**
 * mqq
 * Created by 令令 on 2017/7/20.
 * 这是我的任务
 */
public class MyTaskActivity extends SimpleTopbarActivity {
    @Bind(R.id.but_show_mytask)
    Button showMytask;
    @Bind(R.id.but_save_mytask)
    Button saveMytask;
    @Bind(R.id.listview_mytask)
    ListView listviewMytask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytask_layout);
        ButterKnife.bind(this);
        initListener();
        initDatas();
    }

    private void initDatas() {
        //设置数据
        listviewMytask.setAdapter(new ListViewDataAdapter(this));
    }

    private void initListener() {
        showMytask.setOnClickListener(this);
        saveMytask.setOnClickListener(this);
        showMytask.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_show_mytask:
                  showMytask.setSelected(true);
                  saveMytask.setSelected(false);
                break;
            case R.id.but_save_mytask:
                showMytask.setSelected(false);
                saveMytask.setSelected(true);
                break;
        }

        super.onClick(v);
    }

    @Override
    protected Object getTopbarTitle() {
        return "任务";
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
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
