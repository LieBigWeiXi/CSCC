package com.example.atlas.cscc20.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.atlas.cscc20.Adapter.VodAdapter;
import com.example.atlas.cscc20.Bean.VodInfo;
import com.example.atlas.cscc20.R;
import com.example.atlas.cscc20.ResFromInertet.InternetRes;

import java.util.ArrayList;
import java.util.List;

public class VodActivity extends BaseActivity implements View.OnClickListener{
    VodAdapter adapter;
    GridView vod_list;
    List<VodInfo> vodInfoList=new ArrayList<>();
    InternetRes res = InternetRes.get_instant();
    private TextView all_textView,sing_textView,dance_textView,opera_textView,more_textView;
    //点击标志
    boolean all_tag = false,sing_tag = false,dance_tag = false,opera_tag = false,more_tag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vod_activity);
        vod_list=(GridView)findViewById(R.id.vod_list);
        adapter=new VodAdapter(this);
        vod_list.setAdapter(adapter);
        vodInfoList.addAll(res.vodBean.list.Sing);
        vodInfoList.addAll(res.vodBean.list.Dance);
        vodInfoList.addAll(res.vodBean.list.Stage);
        vodInfoList.addAll(res.vodBean.list.Other);
        all_textView = (TextView) findViewById(R.id.vod_all);
        all_textView.setOnClickListener(this);
        sing_textView = (TextView) findViewById(R.id.tv_vod1);
        sing_textView.setOnClickListener(this);
        dance_textView = (TextView) findViewById(R.id.tv_vod2);
        dance_textView.setOnClickListener(this);
        opera_textView = (TextView) findViewById(R.id.tv_vod3);
        opera_textView.setOnClickListener(this);
        more_textView = (TextView) findViewById(R.id.tv_vod4);
        more_textView.setOnClickListener(this);
        all_textView.setTextColor(Color.rgb(16,151,255));
        findViewById(R.id.all_press_ico_image_view).setVisibility(View.VISIBLE);

        all_tag = true;
        adapter.setArrayList(vodInfoList);
        adapter.notifyDataSetChanged();
        vod_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                VodInfo vodInfo=vodInfoList.get(position);
                VodInfoActivity.vodInfo=vodInfo;
                Intent intent =new Intent(VodActivity.this,VodInfoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.vod_ret).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        TextView v = (TextView)view;
        vodInfoList=new ArrayList<>();
        switch (view.getId()){
            case R.id.vod_all:
                vodInfoList.addAll(res.vodBean.list.Sing);
                vodInfoList.addAll(res.vodBean.list.Dance);
                vodInfoList.addAll(res.vodBean.list.Stage);
                vodInfoList.addAll(res.vodBean.list.Other);
                all_tag = true;
                v.setTextColor(Color.rgb(16,151,255));
                findViewById(R.id.all_press_ico_image_view).setVisibility(View.VISIBLE);
                if(sing_tag){
                    v = (TextView)findViewById(R.id.tv_vod1);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.singe_press_ico_image_view).setVisibility(View.INVISIBLE);
                    sing_tag = false;
                }
                if(dance_tag){
                    v = (TextView)findViewById(R.id.tv_vod2);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.dance_press_ico_image_view).setVisibility(View.INVISIBLE);
                    dance_tag = false;
                }
                if(opera_tag){
                    v = (TextView)findViewById(R.id.tv_vod3);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.opera_press_ico_image_view).setVisibility(View.INVISIBLE);
                    opera_tag = false;
                }
                if(more_tag){
                    v = (TextView)findViewById(R.id.tv_vod4);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.more_press_ico_image_view).setVisibility(View.INVISIBLE);
                    more_tag = false;
                }
                break;
            case R.id.tv_vod1:
                vodInfoList.addAll(res.vodBean.list.Sing);
                sing_tag = true;
                v.setTextColor(Color.rgb(16,151,255));
                findViewById(R.id.singe_press_ico_image_view).setVisibility(View.VISIBLE);
                if(all_tag){
                    v = (TextView)findViewById(R.id.vod_all);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.all_press_ico_image_view).setVisibility(View.INVISIBLE);
                    all_tag = false;
                }
                if(dance_tag){
                    v = (TextView)findViewById(R.id.tv_vod2);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.dance_press_ico_image_view).setVisibility(View.INVISIBLE);
                    dance_tag = false;
                }
                if(opera_tag){
                    v = (TextView)findViewById(R.id.tv_vod3);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.opera_press_ico_image_view).setVisibility(View.INVISIBLE);
                    opera_tag = false;
                }
                if(more_tag){
                    v = (TextView)findViewById(R.id.tv_vod4);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.more_press_ico_image_view).setVisibility(View.INVISIBLE);
                    more_tag = false;
                }
                break;
            case R.id.tv_vod2:
                vodInfoList.addAll(res.vodBean.list.Dance);
                dance_tag = true;
                v.setTextColor(Color.rgb(16,151,255));//浅蓝色
                findViewById(R.id.dance_press_ico_image_view).setVisibility(View.VISIBLE);
                if(all_tag){
                    v = (TextView)findViewById(R.id.vod_all);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.all_press_ico_image_view).setVisibility(View.INVISIBLE);
                    all_tag = false;
                }
                if(sing_tag){
                    v = (TextView)findViewById(R.id.tv_vod1);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.singe_press_ico_image_view).setVisibility(View.INVISIBLE);
                    sing_tag = false;
                }
                if(opera_tag){
                    v = (TextView)findViewById(R.id.tv_vod3);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.opera_press_ico_image_view).setVisibility(View.INVISIBLE);
                    opera_tag = false;
                }
                if(more_tag){
                    v = (TextView)findViewById(R.id.tv_vod4);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.more_press_ico_image_view).setVisibility(View.INVISIBLE);
                    more_tag = false;
                }
                break;
            case R.id.tv_vod3:
                vodInfoList.addAll(res.vodBean.list.Stage);
                opera_tag = true;
                v.setTextColor(Color.rgb(16,151,255));
                findViewById(R.id.opera_press_ico_image_view).setVisibility(View.VISIBLE);
                if(all_tag){
                    v = (TextView)findViewById(R.id.vod_all);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.all_press_ico_image_view).setVisibility(View.INVISIBLE);
                    all_tag = false;
                }
                if(sing_tag){
                    v = (TextView)findViewById(R.id.tv_vod1);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.singe_press_ico_image_view).setVisibility(View.INVISIBLE);
                    sing_tag = false;
                }
                if(dance_tag){
                    v = (TextView)findViewById(R.id.tv_vod2);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.dance_press_ico_image_view).setVisibility(View.INVISIBLE);
                    dance_tag = false;
                }
                if(more_tag){
                    v = (TextView)findViewById(R.id.tv_vod4);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.more_press_ico_image_view).setVisibility(View.INVISIBLE);
                    more_tag = false;
                }
                break;
            case R.id.tv_vod4:
                vodInfoList.addAll(res.vodBean.list.Other);
                more_tag = true;
                v.setTextColor(Color.rgb(16,151,255));
                findViewById(R.id.more_press_ico_image_view).setVisibility(View.VISIBLE);
                if(all_tag){
                    v = (TextView)findViewById(R.id.vod_all);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.all_press_ico_image_view).setVisibility(View.INVISIBLE);
                    all_tag = false;
                }
                if(sing_tag){
                    v = (TextView)findViewById(R.id.tv_vod1);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.singe_press_ico_image_view).setVisibility(View.INVISIBLE);
                    sing_tag = false;
                }
                if(dance_tag){
                    v = (TextView)findViewById(R.id.tv_vod2);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.dance_press_ico_image_view).setVisibility(View.INVISIBLE);
                    dance_tag = false;
                }
                if(opera_tag){
                    v = (TextView)findViewById(R.id.tv_vod3);
                    v.setTextColor(Color.WHITE);
                    findViewById(R.id.opera_press_ico_image_view).setVisibility(View.INVISIBLE);
                    opera_tag = false;
                }
                break;
        }
        adapter.setArrayList(vodInfoList);
        adapter.notifyDataSetChanged();
    }
}
