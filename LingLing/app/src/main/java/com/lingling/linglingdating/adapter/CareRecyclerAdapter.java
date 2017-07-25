package com.lingling.linglingdating.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.linglingdating.R;

/**mqq
 * Created by 令令 on 2017/7/18.
 * 这是互相关心的适配器
 */
public class CareRecyclerAdapter extends RecyclerView.Adapter<CareRecyclerAdapter.MyViewHolder> {
    private Context context;
    private boolean flag;


    public CareRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_layout,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
             holder.im_tou.setImageResource(R.mipmap.tou_xiang);
             holder.txt_name.setText("帅哥");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView im_tou;
        private final TextView txt_name;
        private final TextView txt_care;

        public MyViewHolder(View itemView) {
            super(itemView);
            im_tou = (ImageView) itemView.findViewById(R.id.image_tou_recycle_item);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name_recycle_item);
            txt_care = (TextView) itemView.findViewById(R.id.txt_care_recycle_item);
            //设置点击事件
            txt_care.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag=!flag;
                    int position = getLayoutPosition();
                    if(flag){
                        txt_care.setText("互相关注");
                        txt_care.setSelected(true);
                    }else{
                        txt_care.setText("加关注");
                        txt_care.setSelected(false);
                    }
                }
            });

        }
    }
}
