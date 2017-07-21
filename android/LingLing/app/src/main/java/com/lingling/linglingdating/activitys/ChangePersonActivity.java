package com.lingling.linglingdating.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.activity.BaseActivity;
import com.lingling.linglingdating.imageUtils.CustomConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * mqq
 * Created by 令令 on 2017/7/20.
 * 这是修改个人信息的界面
 */
public class ChangePersonActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.image_back_change_person)
    ImageView imageBackChangePerson;
    @Bind(R.id.txt_over_change_person)
    TextView txtOverChangePerson;
    @Bind(R.id.edit_con_change_person)
    EditText editConChangePerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_person_layout);
        ButterKnife.bind(this);
        initListener();

    }

    private void initListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back_change_person:
                finish();
                break;
            case R.id.txt_over_change_person:
                //回传信息
                String change =editConChangePerson.getText().toString();
                if (!TextUtils.isEmpty(change) && change.length() <= 100) {
                    //需要将数据传递后台保存,并返回给前面界面
                    Intent in = new Intent();
                    in.putExtra("con", change);
                    setResult(CustomConstants.CHANGE_PER_BACK, in);
                    finish();
                }
                break;
        }
    }
}
