package com.example.atlas.cscc20.MyTool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.atlas.cscc20.Activity.MainActivity;
import com.example.atlas.cscc20.Activity.MenuActivity;

public class CheckThread extends Thread{

    private Context context;
    public int num;
    public int flag;

    public CheckThread( int flag, Context context){
        this.flag = flag;
        this.context = context;
    }

    @Override
    public void run() {
        NumUtil numUtil = new NumUtil();
        while (true){
            num = numUtil.getNumber();
//            Log.d("what1","num="+num);
            if (num == 0){
                try {
                    sleep(1000*60*30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num = numUtil.getNumber();
                if (num == 0){
                    Log.d("what2","num="+num);
                    Log.d("what2","static="+numUtil.getNumber());
                    Intent lahuoIntent = new Intent(context, MenuActivity.class);
                    context.startActivity(lahuoIntent);
                    ((Activity) context).finish();
                    numUtil.setNumber(1);
                    Log.d("haveChange","static="+numUtil.getNumber());
                }
            }

        }
    }
}

