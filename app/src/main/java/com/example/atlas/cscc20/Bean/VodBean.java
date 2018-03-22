package com.example.atlas.cscc20.Bean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Atlas on 2017/12/1.
 */

public class VodBean {
    public String status;
    public VodList list;

    public class VodList{
        public ArrayList<VodInfo> Dance;
        public ArrayList<VodInfo> Sing;
        public ArrayList<VodInfo> Other;
        public ArrayList<VodInfo> Stage;
    }
}
