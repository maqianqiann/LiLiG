package com.lingling.linglingdating.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by 令令 on 2017/7/19.
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
      //   Glide.with(context).load(path).into(imageView);
        imageView.setImageResource((Integer) path);

    }
}
