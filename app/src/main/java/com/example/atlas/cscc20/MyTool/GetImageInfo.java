package com.example.atlas.cscc20.MyTool;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Lenovo on 2017/5/31.
 */
public abstract class GetImageInfo {



    public static int getAlpha(Bitmap bitmap, int width, int height) {
        int sum=0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for(int i=0;i<pixels.length;i++){
            int color=pixels[i];
            int A= Color.alpha(color);
            sum+=A;
        }
        return sum/(pixels.length);
    }
}