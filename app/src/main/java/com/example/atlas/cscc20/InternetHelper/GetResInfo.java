package com.example.atlas.cscc20.InternetHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.example.atlas.cscc20.MyTool.PicTool;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Atlas on 2017/12/1.
 */

public class GetResInfo {
    public static void GetRes(final Handler handler, final String url,final int msg_what)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://" + InternetConfig.Server_ip + ":" + String.valueOf(InternetConfig.Port_admin) + "/" + url+"/").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Message message = new Message();
                    message.what = msg_what;
                    message.obj = responseData;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void getPic(final Handler handler, final String url,final int msg_what)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("http://10.82.31.161:8100/img/2017/12/02/20171202104954_27.jpg").build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    public void onFailure(Call call, IOException e) {

                    }
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream inputStream = response.body().byteStream();//得到图片的流
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        Message msg = new Message();
                        msg.obj = PicTool.ChangePicSize(bitmap);
                        msg.what=msg_what;
                        handler.sendMessage(msg);
                    }
                });
            }
        }).start();
    }
}