package com.lingling.linglingdating.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.adapter.PersonTagsAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.BaseActivity;

/**
 * Created by 令令 on 2017/7/18.
 * 附近的人
 */
public class SidePersonActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.image_back_include_more)
    ImageView imageBackIncludeMore;
    @Bind(R.id.txt_dw_include_more)
    TextView txtDwIncludeMore;
    @Bind(R.id.txt_name_include_more)
    TextView txtNameIncludeMore;
    @Bind(R.id.tag_cloud_side_person)
    TagCloudView tagCloudSidePerson;

    private ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_person);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("北京" + i);
        }
        //显示
        txtDwIncludeMore.setVisibility(View.VISIBLE);
        txtNameIncludeMore.setText("附近的人");
        //设置数据
        final PersonTagsAdapter adapter = new PersonTagsAdapter(this, list);
        tagCloudSidePerson.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back_include_more:
                finish();
                break;
        }
    }
}
