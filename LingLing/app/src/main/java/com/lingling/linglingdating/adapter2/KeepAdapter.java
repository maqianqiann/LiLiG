package com.lingling.linglingdating.adapter2;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.activitys.StartDataActivity;
import com.lingling.linglingdating.bean.Per;
import com.lingling.linglingdating.utils.CommonAdapter;
import com.lingling.linglingdating.utils.ViewHolder;

import java.util.List;

/**mqq
 * Created by 令令 on 2017/7/24.
 * 这是进行赴约的人的适配器
 */
public class KeepAdapter extends CommonAdapter<Per> {

  private int num;
    public KeepAdapter(Context context, List<Per> data, int layoutId, int num) {
        super(context, data, layoutId, num);
        this.num=num;
    }

    @Override
    public void convert(ViewHolder holder, int position) {
          holder.setImageResource(R.id.im_tou_keep_item,mData.get(position).im)
                .setText(R.id.txt_time_keep_item,mData.get(position).time).setText(R.id.txt_name_keep_item,mData.get(position).name);
        //设置点击事件,这是接受按钮
        holder.setButton(R.id.but_accept_keep_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "接收成功", Toast.LENGTH_SHORT).show();
                //跳转到开始约会的界面
                mContext.startActivity(new Intent(mContext,StartDataActivity.class));
            }
        });
        Button go_button= holder.setButton(R.id.but_go_away_keep_item);
        if(num==0){
            go_button.setVisibility(View.VISIBLE);
            go_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "走开", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
