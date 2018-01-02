package com.billionss.weather.util;

import com.billionss.weather.bean.weather.ShowApiWeatherNormalInner;

import java.util.Comparator;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/2/9.
 * Role:按时间排序,从小到大
 */
public class SortComparator implements Comparator {
    @Override
    public int compare(Object lhs, Object rhs) {

        ShowApiWeatherNormalInner a=(ShowApiWeatherNormalInner)lhs;

        ShowApiWeatherNormalInner b=(ShowApiWeatherNormalInner)rhs;



        return Integer.parseInt(a.getDay())-Integer.parseInt(b.getDay());
    }
}