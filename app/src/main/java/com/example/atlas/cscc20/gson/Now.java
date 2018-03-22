package com.example.atlas.cscc20.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 此刻天气
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;//温度
    @SerializedName("cond")
    public More more;//天气情况
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
