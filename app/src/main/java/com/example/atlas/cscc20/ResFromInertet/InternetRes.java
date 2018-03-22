package com.example.atlas.cscc20.ResFromInertet;

import com.example.atlas.cscc20.Bean.CulBean;
import com.example.atlas.cscc20.Bean.PicBean;
import com.example.atlas.cscc20.Bean.VodBean;

/**
 * Created by Atlas on 2017/12/1.
 */

public class InternetRes {
    private InternetRes(){}
    private static InternetRes instant;
    public static InternetRes get_instant()
    {
        if(instant==null){
            instant=new InternetRes();
        }
        return instant;
    }
    public PicBean picBean =new PicBean();

    public CulBean culBean =new CulBean();

    public VodBean vodBean=new VodBean();
}
