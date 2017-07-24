package com.lingling.linglingdating.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.activity.FunActivity;
import com.lingling.linglingdating.activity.LingMainActivity;
import com.lingling.linglingdating.activity.SidePersonActivity;
import com.lingling.linglingdating.activitys.MySearchActivity;
import com.lingling.linglingdating.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 令令 on 2017/7/14.
 * 这是交友界面
 */
public class LingFragment extends Fragment implements View.OnClickListener{

    ImageView includeMessage;
    @Bind(R.id.banner_ling)
    Banner bannerLing;
    @Bind(R.id.listView_ling)
    ListView listViewLing;
    @Bind(R.id.txt_include_person)
    TextView txtIncludePerson;
    @Bind(R.id.image_include_search)
    ImageView imageIncludeSearch;
    @Bind(R.id.image_include_message)
    ImageView imageIncludeMessage;
    private LingMainActivity activity;
    private ArrayList<Integer> list_in;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first, null);
        ButterKnife.bind(this, view);
        activity = (LingMainActivity) getActivity();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置banner的数据
        initDatas();
        //界面显示
        initViews();
        initListenes();
    }
    private void initListenes() {
        imageIncludeMessage.setOnClickListener(this);
        txtIncludePerson.setOnClickListener(this);
        imageIncludeSearch.setOnClickListener(this);
        imageIncludeSearch.setOnClickListener(this);
    }

    private void initViews() {
    }

    private void initDatas() {
        list_in=new ArrayList<>();
        list_in.add(R.mipmap.banner);
        list_in.add(R.mipmap.banner);

        bannerLing.setImageLoader(new GlideImageLoader());
        bannerLing.setImages(list_in);
        bannerLing.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_include_person:
                //跳转到附近的人的界面
              startActivity(new Intent(activity, SidePersonActivity.class));
                break;
            case R.id.image_include_message:
                //跳转到粉丝界面
                startActivity(new Intent(activity, FunActivity.class));
                break;
            //跳转到搜索的界面
            case R.id.image_include_search:
                startActivity(new Intent(activity, MySearchActivity.class));
                break;

        }
    }
}
