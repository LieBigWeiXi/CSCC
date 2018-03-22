package com.example.atlas.cscc20.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 2018/1/17.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;//城市名
    @SerializedName("id")
    public String weatherId;//天气id
    public Update update;//天气更新时间
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
