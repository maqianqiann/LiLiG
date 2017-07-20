package com.lingling.linglingdating.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.bean.TimeDatas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 令令 on 2017/7/17.
 * 这是时间轴的内容的适配器
 */
public class TimeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TimeDatas> list;

    public TimeAdapter(ArrayList<TimeDatas> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        if(list!=null){
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder  holder=null;
        if(convertView==null){
            holder=new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(
                    R.layout.ie, parent, false);
           /* holder.desc= (TextView) convertView.findViewById(R.id.desc_list_item_me);
            holder.time= (TextView) convertView.findViewById(R.id.txt_list_item_time);
            holder.girdView= (GridView) convertView.findViewById(R.id.grid_list_item_me);
            holder.ll_ex= (LinearLayout) convertView.findViewById(R.id.ll_ex);
            holder.line=convertView.findViewById(R.id.v_line);*/


            holder.date = (TextView) convertView
                    .findViewById(R.id.txt_date_time);
            holder.ll_ex = (LinearLayout) convertView.findViewById(R.id.ll_ex);
            holder.content = (TextView) convertView
                    .findViewById(R.id.txt_date_content);
            holder.img_arrow = (ImageView) convertView
                    .findViewById(R.id.img_arrow);
            holder.girdView= (GridView) convertView.findViewById(R.id.grid_list_item_me);
            holder.flag = true;
            holder.img_arrow.setBackgroundResource(R.mipmap.ym);
            holder.line = (View) convertView.findViewById(R.id.v_line);
            holder.title = (RelativeLayout) convertView
                    .findViewById(R.id.rl_title);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

      /*  // 时间轴竖线的layout
      RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
        // 第一条数据，肯定显示时间标题
        if (position == 0) {
       //     holder.desc.setVisibility(View.VISIBLE);
            String format = format("yyyy/MM/dd", list.get(position).getDate());
            holder.time.setText(format);

            params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.ll_ex);
        } else { // 不是第一条数据
            // 本条数据和上一条数据的时间戳相同，时间标题不显示
            if (list.get(position).getDate()
                    .equals(list.get(position - 1).getDate())) {
                holder.desc.setVisibility(View.GONE);
           //     params.addRule(RelativeLayout.ALIGN_TOP, R.id.imageView1);
                params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.ll_ex);
            } else {
                // 本条数据和上一条的数据的时间戳不同的时候，显示数据
                holder.desc.setVisibility(View.VISIBLE);
                holder.time.setText(format("yyyy/MM/dd",
                        list.get(position).getDate()));
         //     params.addRule(RelativeLayout.ALIGN_TOP, R.id.imageView1);
                params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.ll_ex);
            }
        }
        holder.line.setLayoutParams(params);
        holder.desc.setText(list.get(position).getText());
       */

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
        // 第一条数据，肯定显示时间标题
        if (position == 0) {
            holder.title.setVisibility(View.VISIBLE);
            holder.date.setText(format("yyyy-MM-dd",
                    list.get(position).getDate()));
        //    params.addRule(RelativeLayout.ALIGN_TOP, R.id.imageView1);
            params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.ll_ex);
        } else { // 不是第一条数据
            // 本条数据和上一条数据的时间戳相同，时间标题不显示
            if (list.get(position).getDate()
                    .equals(list.get(position - 1).getDate())) {
                holder.title.setVisibility(View.GONE);
          //      params.addRule(RelativeLayout.ALIGN_TOP, R.id.imageView1);
                params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.ll_ex);
            } else {
                // 本条数据和上一条的数据的时间戳不同的时候，显示数据
                holder.title.setVisibility(View.VISIBLE);
                holder.date.setText(format("yyyy-MM-dd",
                        list.get(position).getDate()));
             //   params.addRule(RelativeLayout.ALIGN_TOP, R.id.imageView1);
                params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.ll_ex);
            }
        }
        holder.line.setLayoutParams(params);
        holder.content.setText("9:00");
        holder.girdView.setAdapter(new ImageAdapter(context));
        return convertView;

    }


    class ViewHolder{
   /*     TextView time;
        TextView desc;
        LinearLayout ll_ex;
        View line;
*/
        GridView girdView;
        RelativeLayout title;
        View line;
        ImageView img_arrow;
        TextView date;
        TextView content;

        LinearLayout ll_ex;
        boolean flag;
    }

    @SuppressLint("SimpleDateFormat")
    public String format(String format, String time) {
        String result = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = df.parse(time);
            SimpleDateFormat df1 = new SimpleDateFormat(format);
            result = df1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
