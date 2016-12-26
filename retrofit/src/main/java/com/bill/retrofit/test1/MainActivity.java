package com.bill.retrofit.test1;

import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bill.retrofit.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleClick(View v){
        test2();
    }

    private void test2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.weather.com.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiStores apiStores = retrofit.create(ApiStores.class);
        final Call<WeatherEntity> call = apiStores.getWeatherT("101010100");
        call.enqueue(new Callback<WeatherEntity>() {
            @Override
            public void onResponse(Call<WeatherEntity> call, Response<WeatherEntity> response) {
                Log.d("Bill", "异步请求：" + response.body().toString());
            }

            @Override
            public void onFailure(Call<WeatherEntity> call, Throwable t) {
                Log.d("Bill", "异步请求错误：" + t.getMessage());
            }
        });
    }

    private void test1(){
        Retrofit retrofit = new Retrofit.Builder()
                // 注： Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException
                .baseUrl("http://www.weather.com.cn/")
                .build();
        ApiStores apiStores = retrofit.create(ApiStores.class);
//        final Call<ResponseBody> call = apiStores.getWeather("101010100");
        final Call<ResponseBody> call = apiStores.getIpInfo("192.168.2.111");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<ResponseBody> response = call.execute();
                    if(response.isSuccessful()){
                        String body = response.body().string();
                        Log.d("Bill", "同步请求：" + body);
                    }else{
                        Log.d("Bill", "同步请求错误：" + response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NetworkOnMainThreadException e){
                    e.printStackTrace();
                }
            }
        }).start();

        Call<ResponseBody> newCall = call.clone();
        newCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();
                    Log.d("Bill", "异步请求：" + body);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Bill", "异步请求错误：" + t.getMessage());
            }
        });

    }

}
