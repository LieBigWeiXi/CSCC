package com.example.atlas.cscc20.Bean;

import java.util.ArrayList;

/**
 * Created by Atlas on 2017/12/2.
 */

public class PicBean {
    public String status;
    public  PicList list;

    public class PicList{
        public ArrayList<PicInfo> Scenery;
        public ArrayList<PicInfo> Live;
    }
}
