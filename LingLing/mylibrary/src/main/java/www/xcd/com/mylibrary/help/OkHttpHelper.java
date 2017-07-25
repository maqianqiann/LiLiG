package www.xcd.com.mylibrary.help;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import www.xcd.com.mylibrary.config.HttpConfig;

/**
 * @auther mqq
 * Create at 2017/5/11
 * okHttp网络请求协助类
 */
public class OkHttpHelper {

    private static OkHttpHelper instance = null;

//    private static HttpInterface okHttpFaceHelper = null;

    private static OkHttpClient client = new OkHttpClient();

    public static OkHttpHelper getInstance() {
        if (instance != null) {
            instance = null;
        }
        if (instance == null) {
            synchronized (OkHttpHelper.class) {
                if (instance == null) {
                    instance = new OkHttpHelper();
                }
//                okHttpFaceHelper = okHttpFace;
            }
        }

        return instance;
    }

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                //请求错误
//                case HttpConfig.REQUESTERROR:
//                    IOException error = (IOException) msg.obj;
//                    okHttpFaceHelper.onErrorResult(HttpConfig.REQUESTERROR, error);
//                    break;
//                //解析错误
//                case HttpConfig.PARSEERROR:
//                    okHttpFaceHelper.onParseErrorResult(HttpConfig.PARSEERROR);
//                    break;
//                //网络错误
//                case HttpConfig.NETERROR:
//                    break;
//                //请求成功
//                case HttpConfig.SUCCESSCODE:
//                    Bundle bundle = msg.getData();
//                    int requestCode = bundle.getInt("requestCode");
//                    int returnCode = bundle.getInt("returnCode");
//                    String returnMsg = bundle.getString("returnMsg");
//                    String returnData = bundle.getString("returnData");
//                    Map<String, Object> paramsMaps = (Map) msg.obj;
//                    Log.e("Handler", returnData);
//                    okHttpFaceHelper.onSuccessResult(requestCode, returnCode, returnMsg, returnData, paramsMaps);
//                    break;
//            }
//        }
//    };

    /**
     * 异步GET请求
     *
     * @param url        请求网址
     * @param paramsMaps 请求body
     */
    public void getAsyncHttp(final int requestCode, final String url, final Map<String, Object> paramsMaps,final Handler mHandler) {
        Runnable runnableGet = new Runnable() {
            @Override
            public void run() {
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                builder.method("GET", null);
                Request request = builder.build();
                Call callRequest = client.newCall(request);
                callRequest.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException error) {
                        Message message = new Message();
                        message.what = HttpConfig.REQUESTERROR;
                        message.obj = error;
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = null;
                        if (null != response.cacheResponse()) {
                            result = response.cacheResponse().toString();
                        } else {
                            response.body().string();
                            result = response.networkResponse().toString();
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int returnCode = jsonObject.getInt("status");
                            String returnMsg = null;
                            if (returnCode != 10001) {
                                returnMsg = jsonObject.getString("error");
                            } else {
                                returnMsg = "成功";
                            }
                            String returnData = jsonObject.getString("result");
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putInt("requestCode", requestCode);
                            bundle.putInt("returnCode", returnCode);
                            bundle.putString("returnMsg", returnMsg);
                            bundle.putString("returnData", returnData);
                            message.setData(bundle);
                            message.what = HttpConfig.SUCCESSCODE;
                            message.obj = paramsMaps;
                            mHandler.sendMessage(message);
                        } catch (JSONException e) {
                            mHandler.sendEmptyMessage(HttpConfig.PARSEERROR);
                        }
                    }
                });
            }
        };
        Thread thread = new Thread(runnableGet);
        thread.start();
    }

//    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");

    /**
     * post请求
     *
     * @param url        请求路径
     * @param paramsMaps 请求参数
     */
    public void postAsyncHttp(final int requestCode, final String url, final Map<String, Object> paramsMaps,final Handler mHandler) {
        Runnable runnablePost = new Runnable() {
            @Override
            public void run() {
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                if (paramsMaps == null || paramsMaps.size() == 0) {
//                    builder.post(null);
                } else {
                    okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
                    for (String key : paramsMaps.keySet()) {
                        String value = "";
                        if (paramsMaps.get(key) != null) {
                            value = paramsMaps.get(key).toString();
                        }
                        formEncodingBuilder.add(key, value);
                    }
                    builder.post(formEncodingBuilder.build());
                }
                Request request = builder.build();
                Call postCall = client.newCall(request);
                postCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException error) {
                        Message message = new Message();
                        message.what = HttpConfig.REQUESTERROR;
                        message.obj = error;
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = null;
                        if (null != response.cacheResponse()) {
                            result = response.cacheResponse().toString();
                        } else {
                            result = response.body().string();
//                            result = response.networkResponse().toString();
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int returnCode = jsonObject.getInt("status");
                            String returnMsg = null;
                            if (returnCode != 10001) {
                                returnMsg = jsonObject.getString("error");
                            } else {
                                returnMsg = "成功";
                            }
                            String returnData = null;
                            if (!jsonObject.isNull("result")) {
                                returnData = jsonObject.getString("result");
                            } else {
                                returnData = result;
                            }
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putInt("requestCode", requestCode);
                            bundle.putInt("returnCode", returnCode);
                            bundle.putString("returnMsg", returnMsg);
                            bundle.putString("returnData", returnData);
                            message.setData(bundle);
                            message.what = HttpConfig.SUCCESSCODE;
                            message.obj = paramsMaps;
                            mHandler.sendMessage(message);
                        } catch (JSONException e) {
                            mHandler.sendEmptyMessage(HttpConfig.PARSEERROR);
                        }
                    }
                });
            }
        };
        Thread thread = new Thread(runnablePost);
        thread.start();
    }

    //参数类型
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    /**
     * post请求 上传图片
     *
     * @param url        请求路径
     * @param paramsMaps 请求参数
     */
    public void postAsyncPicHttp(final int requestCode, final String url, final Map<String, Object> paramsMaps,final Handler mHandler) {
        Runnable runnablePost = new Runnable() {
            @Override
            public void run() {
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                if (paramsMaps == null || paramsMaps.size() == 0) {
                    builder.post(null);
                } else {
                    MultipartBody.Builder multiBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    for (String key : paramsMaps.keySet()) {
                        String value = paramsMaps.get(key).toString();
                        if (key.equals("img")) {
                            multiBuilder.addFormDataPart(key, "head.png", RequestBody.create(MEDIA_TYPE_PNG, new File(value)));
                        } else {
                            multiBuilder.addFormDataPart(key, value);
                        }
                    }
                    builder.post(multiBuilder.build());
                }
                Request request = builder.build();
                Call postCall = client.newCall(request);
                postCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException error) {
                        Message message = new Message();
                        message.what = HttpConfig.REQUESTERROR;
                        message.obj = error;
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = null;
                        if (null != response.cacheResponse()) {
                            result = response.cacheResponse().toString();
                        } else {
                            result = response.body().string();
//                            result = response.networkResponse().toString();
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int returnCode = jsonObject.getInt("status");
                            String returnMsg = null;
                            String returnData = "";
                            if (returnCode != 10001) {
                                returnMsg = jsonObject.getString("error");
                            } else {
                                returnMsg = "成功";
                                returnData = jsonObject.getString("result");
                            }
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putInt("requestCode", requestCode);
                            bundle.putInt("returnCode", returnCode);
                            bundle.putString("returnMsg", returnMsg);
                            bundle.putString("returnData", returnData);
                            message.setData(bundle);
                            message.what = HttpConfig.SUCCESSCODE;
                            message.obj = paramsMaps;
                            mHandler.sendMessage(message);
                        } catch (JSONException e) {
                            mHandler.sendEmptyMessage(HttpConfig.PARSEERROR);
                        }
                    }
                });
            }
        };
        Thread thread = new Thread(runnablePost);
        thread.start();
    }
}
