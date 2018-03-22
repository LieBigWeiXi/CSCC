package com.example.atlas.cscc20.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.atlas.cscc20.R;

public class Introduce extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        Button backButton = (Button)findViewById(R.id.introduce_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
