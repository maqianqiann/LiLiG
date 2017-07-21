package com.lingling.linglingdating.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * mqq
 * Created by 令令 on 2017/7/19.
 * 这是3D的球的数据界面，为了方便理解定义为适配器
 */
public class PersonTagsAdapter  extends TagsAdapter{
    private Context context;
    private List<String> list=new ArrayList<>();
     public PersonTagsAdapter(Context context, List<String> list) {
         this.context = context;
         this.list = list;
         Collections.addAll(list);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
    //设置数据,本身是没有子控件的，自行添加
    @Override
    public View getView(final Context context, int position, ViewGroup parent) {
        final TextView text=new TextView(context);
        text.setText("No:"+list.get(position));
        text.setGravity(Gravity.CENTER);
        //可以设置点击事件
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了—》"+text.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        text.setTextColor(Color.GRAY);
        return text;
    }
    // 返回每个Tag实例
    @Override
    public Object getItem(int position) {
        if (list==null){
            return null;
        }
        return list.get(position);
    }

    //针对每个Tag返回一个权重值，该值与ThemeColor和Tag初始大小有关；一个简单的权重值生成方式是对一个数N取余或使用随机数
    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    //Tag主题色发生变化时会回调该方法
    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        view.setBackgroundColor(themeColor);
    }
}
