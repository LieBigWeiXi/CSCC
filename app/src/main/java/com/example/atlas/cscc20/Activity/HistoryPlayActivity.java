package com.example.atlas.cscc20.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.atlas.cscc20.Bean.PicInfo;
import com.example.atlas.cscc20.R;
import com.example.atlas.cscc20.View.ShowImageView;

public class HistoryPlayActivity extends BaseActivity {
    public static PicInfo picInfo = new PicInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_play);
        ShowImageView sgv = new ShowImageView(HistoryPlayActivity.this, 1247,658);//实例化ShowActivity类
        LinearLayout ll = (LinearLayout)findViewById(R.id.history_view);
        ll.addView(sgv);

        findViewById(R.id.iv_ret).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public int dip2px(float dpValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
