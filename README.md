# MVP_Retrofit_RxJava_Example
主要是封装的网络请求Retrofit+Rxjava与MVP架构的结合

附RxJava教学:<br>
[Android函数响应式编程最新RxJava-基本用法](http://www.jianshu.com/p/f1deaca633ae)<br>
[Android函数响应式编程最新RxJava-操作符入门(1)](http://www.jianshu.com/p/6a6a405ba111)<br>
[Android函数响应式编程最新RxJava-操作符入门(2)](http://www.jianshu.com/p/1c1c07e66992)<br>
持续更新中....<br>

[简书地址](http://www.jianshu.com/u/2a55d6e39135)

效果图如下；

![mvp.gif](http://upload-images.jianshu.io/upload_images/2787891-9e13eb78563e53b7.gif?imageMogr2/auto-orient/strip)

**MVP**

![mvp1.png](http://upload-images.jianshu.io/upload_images/2787891-72a958cf978a0fe9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**Retrofit**
Retrofit是Square开发的一个Android和java的REST客户端库。

这两天工作不是很忙，写了一个当前流行的Android MVP+Retrofit(封装)+RxJava实例，mvp和retrofit我就不详细讲的，以后会详细写，下面直接上demo！

**1.分类**<br>

首先我们要规划好包名和类的分类，不要把类随随便便放，如下图：

![example.jpg](http://upload-images.jianshu.io/upload_images/2787891-be84a4acf10bdf92.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

除了上图的模式，我们还可以把所有mvp类放在mvp包下，然后再按照上图写。

**2.添加依赖和权限**

```
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'io.reactivex:rxandroid:1.2.1'
    compile 'io.reactivex:rxjava:1.1.6'
    compile 'com.google.code.gson:gson:2.8.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
```

```
  <uses-permission android:name="android.permission.INTERNET"/>
```

**3.定义Model--实体类 与URL接口**<br>

Model其实就是我们常常写的实体类，一般直接可在AS的GsonFormat插件上生成就可以了.这里我就不贴出来了

URL接口如下：
```
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
```
这就需要我们对Retrofit的注解好好去看一看.

**5.连接通信(已封装)**
其实就是下面的代码，这些我已经封装了，请下载查看。
```
 OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(ApiService.API_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
```

**6.View类**

```
public interface WeatherView {

    void getWeatherSuccess(WeatherModel weatherModel);
}
```
下面是activity:

```
public class MainActivity extends AppCompatActivity  implements WeatherView,View.OnClickListener {
    private Button btn;
    private TextView tv_show;
    private EditText edt;
    private WeatherPresenter weatherpresenter=new WeatherPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        tv_show= (TextView) findViewById(R.id.tv_show);
        btn= (Button) findViewById(R.id.btn);
        edt= (EditText) findViewById(R.id.edt);
        btn.setOnClickListener(this);
    }

    @Override
    public void getWeatherSuccess(WeatherModel weatherModel) {

tv_show.setText("  "+weatherModel.getRetData().getWeather()+"  "+weatherModel.getRetData().getWD());

    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn:
               weatherpresenter.loadWeatherData(MainActivity.this,edt.getText().toString());
               break;
       }
    }
}

```

**7.Presenter类**

首先写一个BasePresenter:
```
public class BasePresenter<V> {
    public V mvpView;

    public void attachView(V mvpView) {

    
        this.mvpView = mvpView;
    }
}
```
下面是WeatherPresenter：

```
public class WeatherPresenter extends BasePresenter<WeatherView>{

    public WeatherPresenter(WeatherView view) {
        attachView(view);
    }

   public void loadWeatherData(final Context context, String cityid) {
       Log.e("1111","111111");
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
```
