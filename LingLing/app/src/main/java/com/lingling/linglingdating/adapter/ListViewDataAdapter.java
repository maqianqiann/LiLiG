package com.lingling.linglingdating.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;

/**
 * Created by 令令 on 2017/7/17.
 * 这是约么的适配器
 */
public class ListViewDataAdapter extends BaseAdapter {
    private Context context;

    public ListViewDataAdapter(Context context) {
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
           ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.list_item_data,null);
         holder.listView= (GridView) convertView.findViewById(R.id.GridView_list_item_data);
         holder.imageView_tou= (ImageView) convertView.findViewById(R.id.tou_list_item_data);
         holder.imageView_s= (ImageView) convertView.findViewById(R.id.s_list_item_data);
         holder.text_desc= (TextView) convertView.findViewById(R.id.desc_list_item_data);
         holder.text_time= (TextView) convertView.findViewById(R.id.time_list_item_data);
         holder.text_name= (TextView) convertView.findViewById(R.id.name_list_item_data);
         holder.text_price= (TextView) convertView.findViewById(R.id.price_list_item_data);
        //打标记
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //复制数据
        holder.text_desc.setText("w我是大帅哥，来相约九八");
        holder.text_price.setText("￥666");
        holder.text_time.setText("一个小时之前");
        holder.listView.setAdapter(new MyAdapter());
        return convertView;
    }

    class ViewHolder{
        GridView listView;
        TextView text_desc;
        ImageView imageView_tou;
        ImageView imageView_s;
        TextView text_price;
        TextView text_name;
        TextView text_time;



    }
   public  class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 4;
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
            MyListViewHolder holder=null;
            if(convertView==null){
                holder=new MyListViewHolder();
               convertView=View.inflate(context,R.layout.listview_item_item,null);
                holder.im_content= (ImageView) convertView.findViewById(R.id.image_listView_list_item_data);
                convertView.setTag(holder);
            }else{
                holder= (MyListViewHolder) convertView.getTag();
            }
            holder.im_content.setImageResource(R.mipmap.t_one);
            return convertView;
        }
    }
    class MyListViewHolder {
        ImageView im_content;
    }

}
