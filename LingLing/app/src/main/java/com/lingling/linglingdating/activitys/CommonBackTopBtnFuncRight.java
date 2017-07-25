package com.lingling.linglingdating.activitys;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;
public abstract class CommonBackTopBtnFuncRight extends BaseTopImageBtnFunc {

	public CommonBackTopBtnFuncRight(Activity activity) {
		super(activity);
	}

	@Override
	public int getFuncId() {
		return R.id.topbar_back_right;
	}

	@Override
	public int getFuncIcon() {
		return R.mipmap.nointerest;
	}

	//可以设置点击事件
	@Override
	public void onclick(View v) {
      getActivity().startActivity(new Intent(getActivity(),UnInterestActivity.class));
	}

}
