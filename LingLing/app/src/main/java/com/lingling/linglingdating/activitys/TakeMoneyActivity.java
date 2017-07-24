package com.lingling.linglingdating.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lingling.linglingdating.R;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

/**mqq
 * Created by 令令 on 2017/7/21.
 * 这是提现的界面
 */
public class TakeMoneyActivity extends SimpleTopbarActivity {

    @Bind(R.id.edit_take_money)
    EditText editTakeMoney;
    @Bind(R.id.but_take_money)
    Button butTakeMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takemoney_layout);
        ButterKnife.bind(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_take_money:
                //提现
                String take_moeny = editTakeMoney.getText().toString();
                Integer in=Integer.parseInt(take_moeny);
                //进行提现的操作
                break;
        }
        super.onClick(v);
    }
    @Override
    protected Object getTopbarTitle() {
        return "提现";
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
