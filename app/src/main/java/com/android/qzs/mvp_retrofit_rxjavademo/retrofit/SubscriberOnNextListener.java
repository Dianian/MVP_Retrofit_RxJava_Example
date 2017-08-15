package com.android.qzs.mvp_retrofit_rxjavademo.retrofit;

/**
 * Created by QZS
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
   // void onError(Throwable e);

}
