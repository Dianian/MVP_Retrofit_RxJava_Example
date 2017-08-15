package com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.qzs.mvp_retrofit_rxjavademo.base.BasePresenter;
import com.android.qzs.mvp_retrofit_rxjavademo.retrofit.ProgressSubscriber;
import com.android.qzs.mvp_retrofit_rxjavademo.retrofit.SubscriberOnNextListener;
import com.android.qzs.mvp_retrofit_rxjavademo.utils.RetrofitUtil;
import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.model.WeatherModel;
import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.view.WeatherView;

/**
 * Created by QZS
 */

public class WeatherPresenter extends BasePresenter<WeatherView>{




    public WeatherPresenter(WeatherView view) {
        attachView(view);
    }

   public void loadWeatherData(final Context context, String cityid) {
       Log.e("1111","111111");
     //  apiStores = ApiClient.retrofit().create(ApiService.class);

//       BasePresenter.getInstance().BaseLoadWeatherData(cityid,new ProgressSubscriber<WeatherModel>(new SubscriberOnNextListener<WeatherModel>() {
//           @Override
//           public void onNext(WeatherModel weatherModel) {
//               Log.e("1111","333333"+weatherModel.getRetData().getCity()+" "+weatherModel.getRetData().getDate()+"  "+weatherModel.getRetData().getWeather());
//
//                       mvpView.getWeatherSuccess(weatherModel);
//              // weatherView.getWeatherSuccess(weatherModel);
//           }
//       },context));
       RetrofitUtil.getInstance().BaseLoadWeatherData(cityid,new ProgressSubscriber<WeatherModel>(new SubscriberOnNextListener<WeatherModel>() {
           @Override
           public void onNext(final WeatherModel weatherModel) {
               Log.e("1111","333333"+weatherModel.getRetData().getCity()+" "+weatherModel.getRetData().getDate()+"  "+weatherModel.getRetData().getWeather());

                       mvpView.getWeatherSuccess(weatherModel);
           }
       },context));
//
   }

}
