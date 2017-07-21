package com.lingling.linglingdating.bean;

import android.widget.ImageView;

/**
 * Created by 令令 on 2017/7/17.
 * 时间轴的bean
 */
public class TimeDatas {
    private String date;
    private String text;

    public TimeDatas(String date, String text) {
        this.date = date;
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }
}
