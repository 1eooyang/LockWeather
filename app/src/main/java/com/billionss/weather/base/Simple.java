package com.billionss.weather.base;

import android.support.v4.app.Fragment;

import java.io.Serializable;

/**
 * Created by leo on 2017/12/14.
 */

public class Simple implements Serializable{
    private int value;
    private int title;
    private Class<? extends Fragment> clz;
   // public
   public Simple(int value, int title, Class<? extends Fragment> clz) {
       this.value = value;
       this.title = title;
       this.clz = clz;
   }


    public int getValue() {
        return value;
    }

    public int getTitle() {
        return title;
    }

    public Class<? extends Fragment> getClz() {
        return clz;
    }
}
