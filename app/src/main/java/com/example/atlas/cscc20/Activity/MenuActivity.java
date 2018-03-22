package com.example.atlas.cscc20.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atlas.cscc20.Adapter.CulAdapter;
import com.example.atlas.cscc20.InternetHelper.HttpUtil;
import com.example.atlas.cscc20.MyTool.CheckThread;
import com.example.atlas.cscc20.MyTool.DateTool;
import com.example.atlas.cscc20.MyTool.NumUtil;
import com.example.atlas.cscc20.MyTool.UpdateWeatherService;
import com.example.atlas.cscc20.MyTool.Utility;
import com.example.atlas.cscc20.R;
import com.example.atlas.cscc20.gson.Forecast;
import com.example.atlas.cscc20.gson.Weather;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MenuActivity extends BaseActivity implements View.OnClickListener{

    private static final String CSWeatherId = "CN101250101";//和风天气接口中长沙天气id
    public int NumOfThread = 0;
    public int NumOfAct = 0;

    private TextView today_info,today_temp;
    private TextView tomorrow_info,tomorrow_temp;
    private ImageView today_weatherIcon,tomorrow_weatherIcon;
    private TextView date_txt;
    private ImageView introduce,qrcode,contact;
    //向服务器请求天气信息
    public void requestWeather(String weatherId){
        String url = "http://guolin.tech/api/weather?cityid="+weatherId
                +"&key=bc0418b57b2d4918819d3974ac1285d9";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(weather!=null && "ok".equals(weather.status)){
                            SharedPreferences.Editor editor = PreferenceManager.
                                    getDefaultSharedPreferences(MenuActivity.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        }else{
                            Toast.makeText(MenuActivity.this,
                                    "获取信息失败",Toast.LENGTH_SHORT).show();
                            try{
                                Log.d("MenuActivity","weather="+weather+"weather.status="+weather.status);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                });
            }
        });
    }

    private void showWeatherInfo(Weather weather){
        if(weather!=null&&"ok".equals(weather.status)){
            String today_weatherInfo,tomorrow_weatherInfo;//今日和明日的天气信息
            String today_maxDegree,today_minDegree;//今天最高温，最低温
            String tomorrow_maxDegree,tomorrow_minDegree;//明天最高温，最低温
            Forecast today,tomorrow;
            today = weather.forecasts.get(0);
            tomorrow = weather.forecasts.get(1);
            today_weatherInfo = today.more.info;
            tomorrow_weatherInfo = tomorrow.more.info;
            Log.d("todayInfo",today_weatherInfo);
            today_maxDegree = today.temperature.max;
            today_minDegree = today.temperature.min;
            tomorrow_maxDegree = tomorrow.temperature.max;
            tomorrow_minDegree = tomorrow.temperature.min;
            today_info.setText(today_weatherInfo);
            tomorrow_info.setText(tomorrow_weatherInfo);
            today_temp.setText(today_maxDegree+"/"+today_minDegree+"℃");
            tomorrow_temp.setText(tomorrow_maxDegree+"/"+tomorrow_minDegree+"℃");
            addWeatherIcon(today_weatherInfo,today_weatherIcon);
            addWeatherIcon(tomorrow_weatherInfo,tomorrow_weatherIcon);
            Intent intent = new Intent(this, UpdateWeatherService.class);
            startService(intent);
        }else{
            Toast.makeText(this,"显示天气信息失败",Toast.LENGTH_SHORT).show();
        }
    }
    private void addWeatherIcon(String weatherInfo,ImageView imageView){
        if(weatherInfo.contains("晴")&&weatherInfo.contains("云")){
            imageView.setImageResource(R.drawable.sun_cloud);
        }else if(weatherInfo.contains("雨")&&weatherInfo.contains("雪")){
            imageView.setImageResource(R.drawable.rain_snow);
        }else if(weatherInfo.contains("阵雨")){
            imageView.setImageResource(R.drawable.rains);
        }else if(weatherInfo.contains("云")){
            imageView.setImageResource(R.drawable.clound);
        }else if(weatherInfo.contains("尘")){
            imageView.setImageResource(R.drawable.storm);
        }else if(weatherInfo.contains("晴")) {
            imageView.setImageResource(R.drawable.sun);
        }else if(weatherInfo.contains("阴")) {
            imageView.setImageResource(R.drawable.overcast);
        }else if(weatherInfo.contains("雨")) {
            imageView.setImageResource(R.drawable.rain);
        }else if(weatherInfo.contains("雾")) {
            imageView.setImageResource(R.drawable.mist);
        }else if(weatherInfo.contains("霾")) {
            imageView.setImageResource(R.drawable.mai);
        }else if(weatherInfo.contains("风")) {
            imageView.setImageResource(R.drawable.windy);
        }else if(weatherInfo.contains("未知")) {
            imageView.setImageResource(R.drawable.unknown);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NumOfAct = 1;
        NumUtil numUtil = new NumUtil();
        numUtil.setNumber(NumOfAct);

        setContentView(R.layout.activity_menu);
        ImageView iv_prop=(ImageView) findViewById(R.id.iv_prop);
        iv_prop.setOnClickListener(this);

        findViewById(R.id.iv_his).setOnClickListener(this);
        findViewById(R.id.iv_cul).setOnClickListener(this);
        findViewById(R.id.iv_pano).setOnClickListener(this);
        findViewById(R.id.iv_vod).setOnClickListener(this);

        introduce = (ImageView)findViewById(R.id.introduce);
        qrcode = (ImageView)findViewById(R.id.qrcode);
        contact = (ImageView)findViewById(R.id.contact);
        introduce.setOnClickListener(this);
        qrcode.setOnClickListener(this);
        contact.setOnClickListener(this);

        today_info = (TextView)findViewById(R.id.today_info);
        today_temp = (TextView)findViewById(R.id.today_temp);
        tomorrow_info = (TextView)findViewById(R.id.tomorrow_info);
        tomorrow_temp = (TextView)findViewById(R.id.tomorrow_temp);
        today_weatherIcon = (ImageView)findViewById(R.id.today_weatherIcon);
        tomorrow_weatherIcon = (ImageView)findViewById(R.id.tomorrow_weatherIcon);
        date_txt = (TextView)findViewById(R.id.date_txt);
        date_txt.setText(DateTool.getCurrentDate());
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = pref.getString("weather",null);
        if(weatherString != null){

            Weather weather = Utility.handleWeatherResponse(weatherString);
            showWeatherInfo(weather);
        }else{
            //从服务器查询天气
            requestWeather(CSWeatherId);
        }

        if(NumOfThread == 0){
            new CheckThread(NumOfThread, this).start();
            NumOfThread = 1;
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.iv_prop:
                intent=new Intent(MenuActivity.this,PlayActivity.class);
                PlayActivity.path="http://10.82.31.161:8100/vod/2017/12/02/20171202102219_23.mp4";
                startActivity(intent);
                break;
            case R.id.iv_his:
                intent=new Intent(MenuActivity.this,HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_cul:
                intent=new Intent(MenuActivity.this,CulActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_pano:
                intent=new Intent(MenuActivity.this,PanoItemActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_vod:
                intent=new Intent(MenuActivity.this,VodActivity.class);
                startActivity(intent);
                break;
            case R.id.introduce:
                intent = new Intent(MenuActivity.this,Introduce.class);
                startActivity(intent);
                break;
            case R.id.qrcode:
                intent = new Intent(MenuActivity.this,eqcode.class);
                startActivity(intent);
                break;
            case R.id.contact:
                intent = new Intent(MenuActivity.this,contact.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        NumOfAct = 0;
        Log.d("ActivityStop", ""+NumOfAct);
        NumUtil numUtil = new NumUtil();
        numUtil.setNumber(NumOfAct);
        Log.d("NumUtilStop", ""+numUtil.getNumber());
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        NumOfAct = 1;
        Log.d("ActivityRestart", ""+NumOfAct);
        NumUtil numUtil = new NumUtil();
        numUtil.setNumber(NumOfAct);
        Log.d("NumUtilRestart", ""+numUtil.getNumber());
        Log.d("BaseActivity","onReStart");
    }
}
