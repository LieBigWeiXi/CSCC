package com.example.atlas.cscc20.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.atlas.cscc20.R;

public class contact extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Button backButton = (Button)findViewById(R.id.contact_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
