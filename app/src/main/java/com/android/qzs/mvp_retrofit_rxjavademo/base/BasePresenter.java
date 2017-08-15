package com.android.qzs.mvp_retrofit_rxjavademo.base;

import android.util.Log;

import com.android.qzs.mvp_retrofit_rxjavademo.retrofit.ApiClient;
import com.android.qzs.mvp_retrofit_rxjavademo.retrofit.ApiService;
import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.model.WeatherModel;


import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by QZS
 */
public class BasePresenter<V> {
    public V mvpView;

    protected ApiService apiStores;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView) {
//Log.e("加载了","哈哈哈哈哈哈哈");
      //  apiStores = ApiClient.retrofit().create(ApiService.class);
        this.mvpView = mvpView;
    }

    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }




    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
    public void BaseLoadWeatherData(String cityid,Subscriber<WeatherModel> subscriber){
        Log.e("1111","2222222");
        apiStores.loadDataByRetrofitRxjava(cityid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> void addSubscription(Observable<T> observable, Subscriber<T> subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
