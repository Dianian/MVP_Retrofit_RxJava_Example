package com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.presenter;

import android.util.Log;

import com.android.qzs.mvp_retrofit_rxjavademo.base.BasePresenter;
import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.model.WeatherModel;
import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.view.WeatherView;

import rx.Subscriber;

/**
 * Created by QZS
 */

public class WeatherPresenter extends BasePresenter<WeatherView>{




    public WeatherPresenter(WeatherView view) {
        attachView(view);
    }



    public void loadDataByRetrofitRxjava11111(String cityId) {
        addSubscription(apiStores.loadDataByRetrofitRxjava(cityId), new Subscriber<WeatherModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WeatherModel weatherModel) {
          //  Log.e("请求成功","111");
                mvpView.getWeatherSuccess(weatherModel);
            }
        });

    }
}