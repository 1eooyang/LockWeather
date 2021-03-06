package com.billionss.weather.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.billionss.weather.R;
import com.billionss.weather.activity.BlankActivity;
import com.billionss.weather.adapter.AllCityAdapter;
import com.billionss.weather.base.BaseFragment;
import com.billionss.weather.base.Simple;
import com.billionss.weather.helper.EventCenter;
import com.billionss.weather.helper.UIHelper;
import com.billionss.weather.mvp.presenter.impl.CityPresenterImpl;
import com.billionss.weather.mvp.view.CityView;
import com.billionss.weather.util.Constants;

import java.util.List;

import butterknife.Bind;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/2/14.
 * Role:
 */
public class AllCityFragment extends BaseFragment implements CityView {


    public static AllCityFragment allCityFragment;

    public synchronized static AllCityFragment getInstance(Bundle bundle) {
        if(allCityFragment==null){
            allCityFragment = new AllCityFragment();
            allCityFragment.setArguments(bundle);
        }
        return allCityFragment;
    }

    @Bind(R.id.recycler_view)
    RecyclerView recycler_view;

    @Bind(R.id.add_city)
    TextView add_city;

    private AllCityAdapter allCityAdapter;

    private CityPresenterImpl cityPresenter;


    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_city;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        UIHelper.setTitle(getActivity(),"选择城市");

        initView();
        initData();

    }

    private void initView() {
        initialRecyclerViewLinearLayout(recycler_view);
        add_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), BlankActivity.class);
                intent.putExtra(BlankActivity.BUNDLE_KEY_PAGE, new Simple(2, R.string.select_city, SelectCityFragment.class));
                startActivity(intent);

                //UIHelper.jumpBlankAcitivty(getActivity(), SimpleBackPage.SELECTCITY);
            }
        });
    }

    private void initData() {
        allCityAdapter=new AllCityAdapter(getActivity());
        recycler_view.setAdapter(allCityAdapter);

        cityPresenter=new CityPresenterImpl(getActivity(),this);
        cityPresenter.initialMain();

    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        switch (eventCode) {
            case Constants.EVENTBUS_DELETE_CITY:
                String cityName= (String) eventCenter.getData();
                //在Sp中删除该数据,在主界面中删除数据
                cityPresenter.deleteCity(cityName);
                break;
            case Constants.EVENTBUS_ADD_CITY:
                boolean isHaveName=false;
                String addCityName= (String) eventCenter.getData();
                if(allCityAdapter!=null&&allCityAdapter.getDatas().size()>0){
                    for(String s: (List<String>)allCityAdapter.getDatas()){
                        if(s.equals(addCityName)||s.contains(addCityName)||addCityName.contains(s)){
                            //说明已经存在
                            isHaveName=true;
                            break;
                        }
                    }
                }
                if(isHaveName){
                    return ;
                }
                cityPresenter.addCity(addCityName);

                cityPresenter.initialMain();//重新绘制
                break;
        }
    }

    @Override
    public void addCity(List<String> cityNames) {
        allCityAdapter.setDatas(cityNames);
        allCityAdapter.notifyDataSetChanged();
    }
}
