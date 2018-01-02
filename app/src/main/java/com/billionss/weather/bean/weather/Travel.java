package com.billionss.weather.bean.weather;

import java.io.Serializable;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2016/12/1.
 * Role:
 */
public class Travel implements Serializable {
    private String desc;

    private String title;

    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
}
