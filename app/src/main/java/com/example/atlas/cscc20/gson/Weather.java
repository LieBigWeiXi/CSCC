package com.example.atlas.cscc20.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 天气实体类
 */

public class Weather {

    public String status;
    public Basic basic;
    public Now now;
    public AQI aqi;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecasts;
}
