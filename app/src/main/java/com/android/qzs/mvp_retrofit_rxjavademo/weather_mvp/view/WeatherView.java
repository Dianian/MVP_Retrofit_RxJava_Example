package com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.view;

import android.content.Context;

import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.model.WeatherModel;

/**
 * Created by QZS
 */
public interface WeatherView {

    void getWeatherSuccess(WeatherModel weatherModel);

}
