package com.billionss.weather.util;


import com.billionss.weather.R;

import java.util.Random;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class AdUtils {

    private static int[] icons = {R.drawable.icon0,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5};
    private static String[] urls = {"http://imageplus.baidu.com/output/lincoln_v3.html",
    "http://www.sh.10086.cn/sh/zthd/wap/wxltchlw/",
    "http://emall.shanghai.wxcs.cn/emall/mall/ActMobileSave/index.html",
    "http://www.dinghaochem.com/",
    "https://m.haoinvest.com/hd/down.html"};

    public String url;
    public int icon;

    private AdUtils(String url,int icon){
        this.url = url;
        this.icon = icon;
    }

    public static AdUtils get(){
        int i = new Random().nextInt(5);
        return new AdUtils(urls[i],icons[i]);
    }
}
