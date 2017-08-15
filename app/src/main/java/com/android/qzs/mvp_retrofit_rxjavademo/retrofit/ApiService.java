package com.android.qzs.mvp_retrofit_rxjavademo.retrofit;

import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.model.WeatherModel;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by QZS
 */

public interface ApiService {

    //baseUrl
    String API_SERVER_URL = "http://apistore.baidu.com/microservice/";

    //加载天气
    @GET("weather")
    Observable<WeatherModel> loadDataByRetrofitRxjava(@Query("citypinyin") String cityId);






//    @FormUrlEncoded
//    @POST("user/login")
//    Observable<WeatherModel> getlogin(@Field("oper_name") String page, @Field("oper_pwds") String rows);
}
