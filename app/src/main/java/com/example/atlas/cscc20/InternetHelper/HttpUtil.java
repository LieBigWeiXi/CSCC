package com.example.atlas.cscc20.InternetHelper;

/**
 * Created by DELL on 2018/1/19.
 */

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 和服务器的交互工具类
 */

public class HttpUtil {

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        //java回调机制：enqueue()方法内部已经开启了子线程，并将服务器返回的结果回调给callback
        client.newCall(request).enqueue(callback);
    }
}
