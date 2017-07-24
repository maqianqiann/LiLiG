package com.lingling.linglingdating.adapter2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;

import java.util.ArrayList;

/**
 * mqq
 * Created by 令令 on 2017/7/20.
 * 评论的适配器
 */
public class ContentAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> list;
    public ContentAdapter(Context context, ArrayList<String> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder=null;
        if(convertView==null){
            holder=new MyViewHolder();
            convertView=View.inflate(context, R.layout.list_content_item,null);
            holder.im= (ImageView) convertView.findViewById(R.id.im_tou_listcon_item);
            holder.time= (TextView) convertView.findViewById(R.id.txt_time_listcon_item);
            holder.con= (TextView) convertView.findViewById(R.id.txt_con_listcon_item);
            holder.name= (TextView) convertView.findViewById(R.id.txt_name_listcon_item);
            convertView.setTag(holder);
        }else{
            holder= (MyViewHolder) convertView.getTag();
        }
        holder.im.setImageResource(R.mipmap.tou_xiang);
        holder.con.setText(list.get(position));
        return convertView;
    }

    class MyViewHolder{
        ImageView im;
        TextView time;
        TextView con;
        TextView name;
    }
}
