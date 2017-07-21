package com.lingling.linglingdating.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.activity.LingMainActivity;
import com.lingling.linglingdating.activity.ShowDataActivity;
import com.lingling.linglingdating.activitys.ContentActivity;
import com.lingling.linglingdating.adapter.ListViewDataAdapter;
import com.lingling.linglingdating.view.MyListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * mqq
 * Created by 令令 on 2017/7/14.
 * 这是约么的界面
 */
public class DataFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.listView_data)
    MyListView listViewData;
    @Bind(R.id.show_image_data)
    ImageView showImageData;
    private View view;
    private LingMainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_data, null);
        activity = (LingMainActivity) getActivity();
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ListViewDataAdapter adapter = new ListViewDataAdapter(activity);
        listViewData.setAdapter(adapter);

        //设置点击事件
        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情界面
               Intent in=new Intent(activity,ContentActivity.class);
                //将对应的数据传入
                in.putExtra("position",position);
                startActivity(in);
            }
        });

        //发布约会需求
        showImageData.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_image_data:
                startActivity(new Intent(activity, ShowDataActivity.class));
                break;
        }
    }
}
