package com.example.atlas.cscc20.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.atlas.cscc20.Bean.VodInfo;
import com.example.atlas.cscc20.InternetHelper.InternetConfig;
import com.example.atlas.cscc20.R;

public class VodInfoActivity extends BaseActivity {
    public static VodInfo vodInfo = new VodInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_info);
        Glide.with(this).load("http://"+ InternetConfig.Server_ip+":8100/"+vodInfo.icon)
                .into((ImageView)findViewById(R.id.vod_img));
        TextView vod_bt1=(TextView)findViewById(R.id.vod_actor);
        vod_bt1.setText(vodInfo.actor);//演出单位
        TextView vod_bt2=(TextView)findViewById(R.id.vod_type);//标题中的视频类型
        vod_bt2.setText(vodInfo.type);
        TextView vod_bt4=(TextView)findViewById(R.id.vod_infos);//视频描述
        vod_bt4.setText(vodInfo.info);
        TextView vod_bt5=(TextView)findViewById(R.id.vod_data);//日期
        vod_bt5.setText(vodInfo.data);
        TextView vod_bt6=(TextView)findViewById(R.id.vod_director);//导演
        vod_bt6.setText(vodInfo.director);
        TextView type_textView = (TextView)findViewById(R.id.vod_type2) ;//描述中的视频类型
        type_textView.setText(vodInfo.type);
        findViewById(R.id.vod_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VodInfoActivity.this,PlayActivity.class);
                PlayActivity.path="http://183.215.138.233:8100/"+vodInfo.video;
                startActivity(intent);
            }
        });
        findViewById(R.id.vod_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
