package com.billionss.weather.mvp.interactor;

import com.billionss.weather.bean.SortModel;

import java.util.List;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/3/7.
 * Role:
 */
public interface SelectCityInteractor {

    List<SortModel> getData(String[] data);
}
