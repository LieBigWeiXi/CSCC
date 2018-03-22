package com.example.atlas.cscc20.Activity;

import android.content.CursorLoader;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.atlas.cscc20.Bean.CulBean;
import com.example.atlas.cscc20.Bean.CulInfo;
import com.example.atlas.cscc20.Bean.PicBean;
import com.example.atlas.cscc20.Bean.PicInfo;
import com.example.atlas.cscc20.Bean.VodBean;
import com.example.atlas.cscc20.InternetHelper.GetResInfo;
import com.example.atlas.cscc20.InternetHelper.InternetConfig;
import com.example.atlas.cscc20.MyTool.CheckThread;
import com.example.atlas.cscc20.MyTool.PicTool;
import com.example.atlas.cscc20.R;
import com.example.atlas.cscc20.ResFromInertet.InternetRes;
import com.google.gson.Gson;


public class MainActivity extends BaseActivity {

    InternetRes res=InternetRes.get_instant();
    TextView tv_load;
    private int pic_amt = 0 , now_pic = 0 ,new_old = 0,msg_arg = 1;

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Message message =new Message();
            switch (msg.what)
            {
                //200为获取图片消息
                case 200:
                    String data=(String)msg.obj;
                    res.picBean=new Gson().fromJson(data,PicBean.class);
                    if(res.picBean.status.equals("ok")){
                        tv_load.setText("正在加载风景名胜...");
                        pic_amt =res.picBean.list.Scenery.size();
                        if(pic_amt >0)
                        {
                             getSceneryPic(now_pic);
                        }else{
                            message.what=202;
                            handler.sendMessage(message);
                        }
                    }else {
                        finish();
                    }
                    break;
                case 201:
                    new_old+=1;
                    if(new_old>=2)
                    {
                        now_pic++;
                        new_old = 0;
                        if (now_pic==pic_amt)
                        {
                            tv_load.setText("风景名胜加载完成");
                            message.what=202;
                            handler.sendMessage(message);
                            break;
                        }else {
                            tv_load.setText("正在加载风景名胜："+String.valueOf(now_pic)+"/"+String.valueOf(pic_amt));
                            getSceneryPic(now_pic);
                        }
                    }
                    break;
                case 202:
                    now_pic =0;
                    new_old =0;
                    pic_amt =res.picBean.list.Live.size();
                    if(pic_amt >0)
                    {
                        getLivePic(now_pic);
                    }else{
                        message.what=300;
                        handler.sendMessage(message);
                    }
                    break;
                case 203://加载老照片
                    new_old+=1;
                    if(new_old>=2)
                    {
                        now_pic++;
                        new_old = 0;
                        if (now_pic==pic_amt)
                        {
                            tv_load.setText("名人故居加载完成");
                            message.what=300;
                            handler.sendMessage(message);
                            break;
                        }else {
                            tv_load.setText("正在加载名人故居："+String.valueOf(now_pic)+"/"+String.valueOf(pic_amt));
                            getLivePic(now_pic);
                        }
                    }
                    break;
                case 300:
                    GetResInfo.GetRes(handler,"getCul",301);
                    tv_load.setText("正在加载视频点播列表");
                    break;
                case  301:
                    String cul_data=(String)msg.obj;
                    res.culBean =new Gson().fromJson(cul_data,CulBean.class);
                    pic_amt = res.culBean.list.size();
                    now_pic = 0;
                    if(pic_amt>0)
                    {
                        CulInfo culInfo=res.culBean.list.get(now_pic);
                        getPublicPic(culInfo.instr,302);
                    }else {
                        SendMesgTo(400);
                    }
                    break;
                case 302:
                    Bitmap cul_bitmap=(Bitmap)msg.obj;
                    res.culBean.list.get(now_pic).instr_BitMap =PicTool.ChangePicSize(cul_bitmap);
                    now_pic ++;
                    if(now_pic>=pic_amt){
                        SendMesgTo(400);
                        tv_load.setText("文化名人模块加载完成");
                    }else {
                        getPublicPic(res.culBean.list.get(now_pic).instr,302);
                        tv_load.setText("正在加载文化名人模块："+String.valueOf(now_pic)+"/"+String.valueOf(pic_amt));
                    }
                    break;
                case 400:
                    GetResInfo.GetRes(handler,"getVod",401);
                    tv_load.setText("视频数据开始加载");
                    break;
                case 401:
                    String vod_data=(String) msg.obj;
                    res.vodBean=new Gson().fromJson(vod_data, VodBean.class);
                    tv_load.setText("视频数据加载完成");
                    SendMesgTo(500);
                    break;
                case 500:
                    Intent intent=new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    public void SendMesgTo(int msg_waht){
        Message message=new Message();
        message.what=msg_waht;
        message.arg1=msg_arg;
        handler.sendMessage(message);
    }
    public void getSceneryPic(final int position)
    {
        PicInfo picInfo=res.picBean.list.Scenery.get(position);
        String new_pic=picInfo.new_pic;
        String old_pic=picInfo.old_pic;
        Glide.with(MainActivity.this)
                .load("http://"+ InternetConfig.Server_ip+":8100/"+ new_pic)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        res.picBean.list.Scenery.get(position).new_bitmap = PicTool.ChangePicSize(bitmap);
                        Message msg=new Message();
                        msg.what=201;
                        msg.arg1=msg_arg;
                        handler.sendMessage(msg);
                    }
                }); //加载新照片
        Glide.with(MainActivity.this)
                .load("http://"+ InternetConfig.Server_ip+":8100/"+ old_pic)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        res.picBean.list.Scenery.get(position).old_bitMap = PicTool.ChangePicSize(bitmap);
                        Message msg=new Message();
                        msg.what=201;
                        handler.sendMessage(msg);
                    }
                }); //加载老照片
    }

    public void getPublicPic(final String url ,final int msg_what){
        Glide.with(MainActivity.this)
                .load("http://"+ InternetConfig.Server_ip+":8100/"+ url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        Message msg=new Message();
                        msg.what=msg_what;
                        msg.arg1=msg_arg;
                        msg.obj =PicTool.ChangePicSize(bitmap);
                        handler.sendMessage(msg);
                    }
                }); //加载新照片
    }
    public void getLivePic(final int position)
    {
        PicInfo picInfo=res.picBean.list.Live.get(position);
        String new_pic=picInfo.new_pic;
        String old_pic=picInfo.old_pic;
        Glide.with(MainActivity.this)
                .load("http://"+ InternetConfig.Server_ip+":8100/"+ new_pic)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        res.picBean.list.Live.get(position).new_bitmap = PicTool.ChangePicSize(bitmap);
                        Message msg=new Message();
                        msg.what=203;
                        handler.sendMessage(msg);
                    }
                }); //加载新照片
        Glide.with(MainActivity.this)
                .load("http://"+ InternetConfig.Server_ip+":8100/"+ old_pic)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        res.picBean.list.Live.get(position).old_bitMap = PicTool.ChangePicSize(bitmap);
                        Message msg=new Message();
                        msg.what=203;
                        handler.sendMessage(msg);
                    }
                }); //加载老照片
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_load=(TextView)findViewById(R.id.load_text);
        GetResInfo.GetRes(handler,"getPic",200);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}
