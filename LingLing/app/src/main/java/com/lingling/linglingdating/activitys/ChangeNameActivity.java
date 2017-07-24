package com.lingling.linglingdating.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.imageUtils.CustomConstants;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.BaseActivity;

/**
 * mqq
 * Created by 令令 on 2017/7/19.
 * 这是修改名称的界面
 */
public class ChangeNameActivity extends BaseActivity {
    @Bind(R.id.image_back_change_name)
    ImageView imageBackChangeName;
    @Bind(R.id.txt_over_change_name)
    TextView txtOverChangeName;
    @Bind(R.id.edit_xiu_change_name)
    EditText editXiuChangeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_name_layout);
        ButterKnife.bind(this);
        txtOverChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String change= editXiuChangeName.getText().toString();
                if(!TextUtils.isEmpty(change)&&change.length()<=20){
                    //需要将数据传递后台保存,并返回给前面界面
                    Intent in=new Intent();
                    in.putExtra("name",change);
                    Log.i("name",change);
                    setResult(CustomConstants.CHANGE_NAME_BACK,in);
                    finish();
                }

            }
        });
    }

}
