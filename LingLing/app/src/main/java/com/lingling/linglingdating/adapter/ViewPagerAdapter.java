package com.lingling.linglingdating.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 令令 on 2017/7/13.
 * 引导页的适配器
 * by ling
 */
public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<ImageView> list;

    public ViewPagerAdapter(Context context, ArrayList<ImageView> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
          container.removeView(list.get(position));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = list.get(position);
        container.addView(view);
        return view;
    }
}
