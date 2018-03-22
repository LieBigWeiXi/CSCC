package com.example.atlas.cscc20.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.atlas.cscc20.Adapter.CulAdapter;
import com.example.atlas.cscc20.R;
import com.example.atlas.cscc20.ResFromInertet.InternetRes;

public class CulActivity extends BaseActivity {
    InternetRes res=InternetRes.get_instant();
    ImageView iv_show;
    RecyclerView cul_list;
    CulAdapter adapter;
    private int mScreenWidth;
    private static final float MIN_SCALE = .95f;
    private static final float MAX_SCALE = 1.15f;
    private int mMinWidth;
    private int mMaxWidth;
    int height;
    Handler hander =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    iv_show.setImageBitmap(res.culBean.list.get(msg.arg1).instr_BitMap);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cul);

        iv_show = (ImageView) findViewById(R.id.show_image);
        if(res.culBean.list.size()>0){
            iv_show.setImageBitmap(res.culBean.list.get(0).instr_BitMap);
        }
        cul_list = (RecyclerView) findViewById(R.id.cul_list);
        adapter = new CulAdapter(this, res.culBean.list, hander);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        cul_list.setLayoutManager(layoutManager);
        cul_list.setAdapter(adapter);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mMinWidth = (int) (mScreenWidth * 0.20f);
        mMaxWidth = mScreenWidth - 4 * mMinWidth;
        findViewById(R.id.cul_ret).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cul_list.addOnScrollListener(mOnScrollListener);

    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            final int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                RelativeLayout child = (RelativeLayout) recyclerView.getChildAt(i);
                Log.w("unload","Ok2");
                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
                Log.w("unload","Ok1");
                lp.rightMargin = 5;
                lp.height = 180;
                int left = child.getLeft();
                int right = mScreenWidth - child.getRight();
                final float percent = left < 0 || right < 0 ? 0 : Math.min(left, right) * 1f / Math.max(left, right);
                float scaleFactor = MIN_SCALE + Math.abs(percent) * (MAX_SCALE - MIN_SCALE);
                int width = (int) (mMinWidth + Math.abs(percent) * (mMaxWidth - mMinWidth));
                lp.width = width;
                child.setLayoutParams(lp);
                child.setScaleY(scaleFactor);

            }
        }
    };

    public int dp2px(float dipValue) {
        final float scale =this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
