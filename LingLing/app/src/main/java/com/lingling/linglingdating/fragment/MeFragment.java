package com.lingling.linglingdating.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.activity.LingMainActivity;
import com.lingling.linglingdating.activity.MoreActivity;
import com.lingling.linglingdating.adapter.TimeAdapter;
import com.lingling.linglingdating.bean.TimeDatas;
import com.lingling.linglingdating.utils.TimeComparator;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 令令 on 2017/7/14.
 */
public class MeFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.gridView_me)
    GridView gridViewMe;
    @Bind(R.id.listView_me)
    ListView listViewMe;
    @Bind(R.id.image_more_me)
    ImageView imageMoreMe;
    private LingMainActivity activity;
    private ArrayList<TimeDatas> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_me, null);
        activity = (LingMainActivity) getActivity();
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        TimeDatas date1 = new TimeDatas("20151119", "一切都很完美！！！");
        TimeDatas date2 = new TimeDatas("20151115", "无所事事");
        TimeDatas date3 = new TimeDatas("20151117", "闲着玩呗？");
        TimeDatas date4 = new TimeDatas("20151116", "撒很有意思吗");
        list.add(date1);
        list.add(date2);
        list.add(date3);
        list.add(date4);
        // 将数据按照时间排序
        TimeComparator comparator = new TimeComparator();
        Collections.sort(list, comparator);
        //listView加头
        View view = View.inflate(activity, R.layout.list_head, null);
        listViewMe.addHeaderView(view);
        listViewMe.setAdapter(new TimeAdapter(list, activity));
        initListene();

    }
    private void initListene(){
        imageMoreMe.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_more_me:
                startActivity(new Intent(activity,MoreActivity.class));
                break;

        }
    }
}
