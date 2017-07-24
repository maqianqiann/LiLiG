package com.lingling.linglingdating.adapter2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lingling.linglingdating.R;

import java.util.ArrayList;

/**
 * mqq
 * Created by 令令 on 2017/7/19.
 * 收益的适配器
 */
public class PriceAdapter  extends BaseAdapter{
    private Context context;
    private ArrayList<String> list;

    public PriceAdapter( Context context) {

        this.context = context;
    }
    private int i;
    public PriceAdapter(Context context, int i) {
        this.context = context;
        this.i=i;
    }


    @Override
    public int getCount() {
        return 3;
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
        ViewHolder holder=null;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.me_zh_item,null);
            holder=new ViewHolder();
            holder.sy= (TextView) convertView.findViewById(R.id.txt_sy_me_zh_item);
            holder.price= (TextView) convertView.findViewById(R.id.txt_price_me_zh_item);
            holder.time= (TextView) convertView.findViewById(R.id.txt_time_me_zh_item);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        if(i==2){
            holder.time.setText("2017-08-12");
            holder.sy.setText("租吧收益");
            holder.price.setText("+300.00");
        }else{
            holder.time.setText("2017-07-21");
            holder.sy.setText("约么支出");
            holder.price.setText("-200.00");
        }
        return convertView;
    }
    class ViewHolder{
        TextView sy;
        TextView price;
        TextView time;

    }
}
