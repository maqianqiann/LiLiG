package com.lingling.linglingdating.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lingling.linglingdating.R;

/**
 * Created by 令令 on 2017/7/17.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder=null;
        if(convertView==null){
            holder=new MyViewHolder();
            convertView=View.inflate(context, R.layout.listview_item_item,null);
            holder.im= (ImageView) convertView.findViewById(R.id.image_listView_list_item_data);
            convertView.setTag(holder);
        }else{
            holder= (MyViewHolder) convertView.getTag();
        }
        holder.im.setImageResource(R.mipmap.t_one);
        return convertView;
    }

    class MyViewHolder{
        ImageView im;
    }
}
