package com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.qzs.mvp_retrofit_rxjavademo.R;
import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.model.WeatherModel;
import com.android.qzs.mvp_retrofit_rxjavademo.weather_mvp.presenter.WeatherPresenter;


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

               weatherpresenter.loadDataByRetrofitRxjava11111(edt.getText().toString());

               break;
       }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (weatherpresenter!=null){
            weatherpresenter.detachView();
            Log.e("RXJAVA","毁灭");
        }
    }
}
