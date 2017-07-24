package www.xcd.com.mylibrary.base.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.config.HttpConfig;
import www.xcd.com.mylibrary.func.BaseTopFunc;
import www.xcd.com.mylibrary.func.CommonBackTopBtnFunc;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;
import www.xcd.com.mylibrary.utils.DialogUtil;
import www.xcd.com.mylibrary.utils.NetUtil;

import static www.xcd.com.mylibrary.utils.ToastUtil.showToast;

/**
 * topbar
 * 目标：
 * 1、可以自定义的增加按钮或者文本按钮（支持右侧最多添加两个图片按钮或者一个文本按钮，左侧只能是一个按钮，返回或者目录按钮）
 * 2、自定义按钮自己初始化自己的view并响应事件
 * 3、支持title名称的改变
 * 4、按钮支持文本按钮和imagebutton
 *
 * @author mqq
 * @date 2015年10月10日
 * @version 1.0
 */
public abstract class SimpleTopbarActivity extends BaseActivity implements OnClickListener,View.OnFocusChangeListener,HttpInterface {

	protected Dialog loginDialog;
	/** 右侧功能对象的MAP，可以通过id获得指定的功能对象 */
	protected Hashtable<Integer, BaseTopFunc> funcMap = new Hashtable<Integer, BaseTopFunc>();

	/** title */
	private TextView viewTitle;


	/** 左侧功能区，用来放置功能按钮 */
	protected LinearLayout leftFuncZone;
	/** 右侧功能区，用来放置功能按钮 */
	protected LinearLayout rightFuncZone;
	/** 中间功能区，用来放置功能按钮或者默认的文本域 */
	protected RelativeLayout middlerFuncZone;

	/**
	 * 获得title文本
	 * 
	 * @return
	 */
	protected Object getTopbarTitle() {
		return "";
	}

	/**
	 * 获得左侧的功能控件
	 * 只能是imagebutton，并且只有一个
	 * 
	 * @return
	 */
	protected Class<?> getTopbarLeftFunc() {
		// 默认使用back
		return CommonBackTopBtnFunc.class;
	}

	/**
	 * 获得右侧的功能控件集合
	 * 如果有文本，那么就不能有imagebutton
	 * 如果是imagebutton，那么可以是一个或者两个
	 * 
	 * @return
	 */
	protected Class<?>[] getTopbarRightFuncArray() {
		return null;
	}

	protected Class<?> getTopbarMiddleFunc() {
		return null;
	}


	/**
	 * 重新设置title
	 * 
	 * @param resId
	 */
	public void resetTopbarTitle(int resId) {
		// 设置title
		viewTitle.setText(resId);
	}

	/**
	 * 重新设置title
	 * 
	 * @param text
	 */
	public void resetTopbarTitle(String text) {
		// 设置title
		viewTitle.setText(text);
	}


	@Override
	protected void afterSetContentView() {
		super.afterSetContentView();

		viewTitle = (TextView) findViewById(R.id.topbar_title);
		leftFuncZone = (LinearLayout) findViewById(R.id.left_func);
		rightFuncZone = (LinearLayout) findViewById(R.id.right_func);
		// 判断使用默认的文字title还是使用自定义的title
		if (getTopbarMiddleFunc() != null) {
			viewTitle.setVisibility(View.GONE);

			addCustomViewToMiddleFunctionZone();
		} else {
			viewTitle.setVisibility(View.VISIBLE);

			// 设置title
			if (getTopbarTitle() instanceof Integer) {
				int title = (Integer) getTopbarTitle();

				if (title != 0) {
					viewTitle.setText(title);
				}
			} else if (getTopbarTitle() instanceof String) {
				String title = (String) getTopbarTitle();
				viewTitle.setText(title);
			}
		}

		// 添加左侧控件（默认是返回按钮，但是支持自定义替换此按钮）
		addViewToLeftFunctionZone();
		// 添加右侧控件
		addViewToRightFunctionZone();
	}

	@Override
	public void onClick(View v) {
		BaseTopFunc topFunc = funcMap.get(v.getId());
		if (topFunc != null) {
			topFunc.onclick(v);
		}
	}

	/**
	 * 在中间放置自定义的控件
	 * 
	 */
	private void addCustomViewToMiddleFunctionZone() {
		Class<?> customFunc = getTopbarMiddleFunc();
		View funcView = getFuncView(getLayoutInflater(), customFunc);

		if (funcView != null) {
			// 点击事件
			funcView.setOnClickListener(this);
			// 加入页面
			middlerFuncZone.addView(funcView);

			// 设置列表显示
			middlerFuncZone.setVisibility(View.VISIBLE);
		} else {
			middlerFuncZone.setVisibility(View.GONE);
		}
	}

	/**
	 * 
	 * 将功能控件添加到左侧功能区域
	 * 
	 */
	protected void addViewToLeftFunctionZone() {
		Class<?> customFunc = (Class<?>) getTopbarLeftFunc();
		if (customFunc == null) {
			return;
		}

		View funcView = getFuncView(getLayoutInflater(), customFunc);

		if (funcView != null) {
			// 点击事件
			funcView.setOnClickListener(this);
			// 加入页面
			leftFuncZone.addView(funcView);

			// 设置列表显示
			leftFuncZone.setVisibility(View.VISIBLE);
		} else {
			leftFuncZone.setVisibility(View.GONE);
		}
	}
	/**
	 * 
	 * 将功能控件添加到右侧功能区域
	 * 
	 * @param
	 */
	private void addViewToRightFunctionZone() {
		Class<?>[] customFuncs = getTopbarRightFuncArray();

		// 功能列表为空,隐藏区域
		if (customFuncs == null || customFuncs.length == 0) {
			rightFuncZone.setVisibility(View.GONE);
			return;
		}
		// 初始化功能
		for (Class<?> customFunc : customFuncs) {
			// view
			View funcView = getFuncView(getLayoutInflater(), customFunc);
			if (funcView != null) {
				// 点击事件
				funcView.setOnClickListener(this);
				// 加入页面
				rightFuncZone.addView(funcView);
			}
		}
		// 设置列表显示
		rightFuncZone.setVisibility(View.VISIBLE);
	}
	/**
	 * 获得功能View
	 * 
	 * @param inflater
	 * @param
	 */
	protected View getFuncView(LayoutInflater inflater, Class<?> funcClazz) {
		BaseTopFunc func = BaseTopFunc.newInstance(funcClazz, this);

		if (func == null) {
			return null;
		}

		funcMap.put(func.getFuncId(), func);

		// view
		View funcView = func.initFuncView(inflater);
		return funcView;
	}

	/**
	 * 
	 * 更新topbar控件，暂时全部更新，以后考虑指定更新
	 * 
	 */
	public void refreshFuncView() {
		for (Map.Entry<Integer, BaseTopFunc> entity : funcMap.entrySet()) {
			BaseTopFunc topFunc = entity.getValue();
			topFunc.reView();
		}
	}



	@Override
	public void onFocusChange(View view, boolean hasFocus) {
		EditText textView = (EditText) view;
		if (textView==null){
			return;
		}
		if (textView.getHint()==null){
			return;
		}
		String hint = textView.getHint().toString();

		if (hasFocus) {
			textView.setTag(hint);
			textView.setHint("");
		} else {
			hint = textView.getTag().toString();
			textView.setHint(hint);
		}
		if (!hasFocus) {
			InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}else {
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			view.requestFocus();
			imm.showSoftInput(view, 0);
		}

	}
	public void createDialogshow(){
		// 登录中dialog
		loginDialog = DialogUtil.getProgressDialog(this);
		dialogshow();
	}
	/**
	 * 关闭登录中dialog
	 */
	public void dialogDissmiss() {
		if (loginDialog != null && loginDialog.isShowing()) {
			loginDialog.dismiss();
		}
	}

	/**
	 * 显示登录中dialog
	 */
	public void dialogshow() {
		if (loginDialog != null && !loginDialog.isShowing()) {
			loginDialog.show();
		}
	}
	/**
	 * POST网络请求
	 *
	 * @param url        地址
	 * @param paramsMaps 参数
	 */
	public void okHttpPost(final int requestCode, String url, final Map<String, Object> paramsMaps) {
		if (NetUtil.getNetWorking(SimpleTopbarActivity.this) == false) {
			showToast("请检查网络。。。");
			return;
		}
		OkHttpHelper.getInstance().postAsyncHttp(requestCode, url, paramsMaps, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
					//请求错误
					case HttpConfig.REQUESTERROR:
						IOException error = (IOException) msg.obj;
						onErrorResult(HttpConfig.REQUESTERROR, error);
						break;
					//解析错误
					case HttpConfig.PARSEERROR:
						onParseErrorResult(HttpConfig.PARSEERROR);
						break;
					//网络错误
					case HttpConfig.NETERROR:
						break;
					//请求成功
					case HttpConfig.SUCCESSCODE:
						Bundle bundle = msg.getData();
						int requestCode = bundle.getInt("requestCode");
						int returnCode = bundle.getInt("returnCode");
						String returnMsg = bundle.getString("returnMsg");
						String returnData = bundle.getString("returnData");
						Map<String, Object> paramsMaps = (Map) msg.obj;
						onSuccessResult(requestCode, returnCode, returnMsg, returnData, paramsMaps);
						break;
				}
			}
		});
	}

	/**
	 * GET网络请求
	 *
	 * @param url        地址
	 * @param paramsMaps 参数
	 */
	public void okHttpGet(final int requestCode, String url, final Map<String, Object> paramsMaps) {
		if (NetUtil.getNetWorking(SimpleTopbarActivity.this) == false) {
			showToast("请检查网络。。。");
			return;
		}
		OkHttpHelper.getInstance().getAsyncHttp(requestCode, url, paramsMaps, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
					//请求错误
					case HttpConfig.REQUESTERROR:
						IOException error = (IOException) msg.obj;
						onErrorResult(HttpConfig.REQUESTERROR, error);
						break;
					//解析错误
					case HttpConfig.PARSEERROR:
						onParseErrorResult(HttpConfig.PARSEERROR);
						break;
					//网络错误
					case HttpConfig.NETERROR:
						break;
					//请求成功
					case HttpConfig.SUCCESSCODE:
						Bundle bundle = msg.getData();
						int requestCode = bundle.getInt("requestCode");
						int returnCode = bundle.getInt("returnCode");
						String returnMsg = bundle.getString("returnMsg");
						String returnData = bundle.getString("returnData");
						Map<String, Object> paramsMaps = (Map) msg.obj;
						onSuccessResult(requestCode, returnCode, returnMsg, returnData, paramsMaps);
						break;
				}
			}
		});
	}

	/**
	 * POST网络请求上传图片
	 *
	 * @param url        地址
	 * @param paramsMaps 参数
	 */
	public void okHttpImgPost(final int requestCode, String url, final Map<String, Object> paramsMaps) {
		if (NetUtil.getNetWorking(SimpleTopbarActivity.this) == false) {
			showToast("请检查网络。。。");
			return;
		}
		OkHttpHelper.getInstance().postAsyncPicHttp(requestCode, url, paramsMaps, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
					//请求错误
					case HttpConfig.REQUESTERROR:
						IOException error = (IOException) msg.obj;
						onErrorResult(HttpConfig.REQUESTERROR, error);
						break;
					//解析错误
					case HttpConfig.PARSEERROR:
						onParseErrorResult(HttpConfig.PARSEERROR);
						break;
					//网络错误
					case HttpConfig.NETERROR:
						break;
					//请求成功
					case HttpConfig.SUCCESSCODE:
						Bundle bundle = msg.getData();
						int requestCode = bundle.getInt("requestCode");
						int returnCode = bundle.getInt("returnCode");
						String returnMsg = bundle.getString("returnMsg");
						String returnData = bundle.getString("returnData");
						Map<String, Object> paramsMaps = (Map) msg.obj;
						onSuccessResult(requestCode, returnCode, returnMsg, returnData, paramsMaps);
						break;
				}
			}
		});
	}

}
