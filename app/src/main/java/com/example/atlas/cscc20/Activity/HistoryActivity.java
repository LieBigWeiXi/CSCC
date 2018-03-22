package com.example.atlas.cscc20.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.atlas.cscc20.Adapter.HistoryAdapter;
import com.example.atlas.cscc20.Bean.PicInfo;
import com.example.atlas.cscc20.R;
import com.example.atlas.cscc20.ResFromInertet.InternetRes;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity implements View.OnClickListener{

    private HistoryAdapter historyAdapter;
    private List<PicInfo> picInfoList = new ArrayList<>();
    private GridView gridView;
    private InternetRes res = InternetRes.get_instant();
    boolean  bkg_tag_all = false,bkg_tag_place = false,bak_tag_scenery = false;//记录当前选择的目录
    boolean onCreateDoTag = true;

    @Override
    protected void onStop() {
        super.onStop();
        onCreateDoTag = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_sky_layout);
        onCreateDoTag = false;
        gridView =(GridView) findViewById(R.id.oldpic_gridview);
        historyAdapter = new HistoryAdapter(this);
        gridView.setAdapter(historyAdapter);

        picInfoList.addAll(res.picBean.list.Live);
        picInfoList.addAll(res.picBean.list.Scenery);
        historyAdapter.setArrayList(picInfoList);
        historyAdapter.notifyDataSetChanged();

        LinearLayout layout_allBkg;
        layout_allBkg = (LinearLayout)findViewById(R.id.all_backgroud);
        layout_allBkg.setBackgroundResource(R.drawable.chg_bkg);
        bkg_tag_all = true;

        Button history_all,history_place,history_scenery;
        history_all = (Button)findViewById(R.id.history_all);
        history_place = (Button)findViewById(R.id.history_place);
        history_scenery = (Button)findViewById(R.id.history_scenery);
        history_all.setOnClickListener(this);
        history_place.setOnClickListener(this);
        history_scenery.setOnClickListener(this);

        findViewById(R.id.today_history).setOnClickListener(this);


        Button btn = (Button)findViewById(R.id.his_back);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PicInfo picInfo = picInfoList.get(i);
                HistoryPlayActivity.picInfo = picInfo;
                Intent intent = new Intent(HistoryActivity.this, HistoryPlayActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        picInfoList = new ArrayList<>();
        switch (view.getId()){
            case R.id.history_all:
                picInfoList.addAll(res.picBean.list.Live);
                picInfoList.addAll(res.picBean.list.Scenery);
                bkg_tag_all = true;
                findViewById(R.id.all_backgroud).setBackgroundResource(R.drawable.chg_bkg);
                if(bkg_tag_place){
                    findViewById(R.id.place_backgroud).setBackgroundResource(R.color.transparent);
                    bkg_tag_place = false;
                }
                if(bak_tag_scenery){
                    findViewById(R.id.scenery_background).setBackgroundResource(R.color.transparent);
                    bak_tag_scenery = false;
                }
                break;
            case R.id.history_place :
                picInfoList.addAll(res.picBean.list.Live);
                bkg_tag_place = true;
                findViewById(R.id.place_backgroud).setBackgroundResource(R.drawable.chg_bkg);
                if(bkg_tag_all){
                    findViewById(R.id.all_backgroud).setBackgroundResource(R.color.transparent);
                    bkg_tag_all = false;
                }
                if(bak_tag_scenery){
                    findViewById(R.id.scenery_background).setBackgroundResource(R.color.transparent);
                    bak_tag_scenery = false;
                }
                break;
            case R.id.history_scenery:
                picInfoList.addAll(res.picBean.list.Scenery);
                bak_tag_scenery = true;
                findViewById(R.id.scenery_background).setBackgroundResource(R.drawable.chg_bkg);
                if(bkg_tag_place){
                    findViewById(R.id.place_backgroud).setBackgroundResource(R.color.transparent);
                    bkg_tag_place = false;
                }
                if(bkg_tag_all){
                    findViewById(R.id.all_backgroud).setBackgroundResource(R.color.transparent);
                    bkg_tag_all = false;
                }
                break;
            case R.id.today_history:
                Intent intent = new Intent(HistoryActivity.this,Today_History.class);
                startActivity(intent);
                break;
        }
        historyAdapter.setArrayList(picInfoList);
        historyAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //onCreateDoTag一个onCreate方法的执行的检测标志，避免加载两次图片
      /*  if(onCreateDoTag){
            gridView =(GridView) findViewById(R.id.oldpic_gridview);
            historyAdapter = new HistoryAdapter(this);
            gridView.setAdapter(historyAdapter);
            picInfoList.addAll(res.picBean.list.Live);
            picInfoList.addAll(res.picBean.list.Scenery);
            historyAdapter.setArrayList(picInfoList);
            historyAdapter.notifyDataSetChanged();
        }*/
    }
}
