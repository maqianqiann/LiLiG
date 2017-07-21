package com.lingling.linglingdating.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.adapter.CareRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 令令 on 2017/7/18.
 * 这是粉丝的界面
 */
public class FunActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.image_back_include_more)
    ImageView imageBackIncludeMore;
    @Bind(R.id.rec_fun)
    RecyclerView recFun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        //设置数据,管理者
         recFun.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
         CareRecyclerAdapter adapter=new CareRecyclerAdapter(this);
         recFun.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back_include_more:
                finish();
                break;
        }
    }


}
