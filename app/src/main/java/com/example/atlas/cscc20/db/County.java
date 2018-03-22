package com.example.atlas.cscc20.db;

import org.litepal.crud.DataSupport;

public class County extends DataSupport {
    private int id;//县id
    private String countyName;//县名
    private String weatherId;//县对应的天气id

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

}