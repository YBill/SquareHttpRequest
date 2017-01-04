package com.bill.retrofit.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bill.retrofit.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TestRxActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void handleClick(View view) {
        getMovices3();
    }

    private void getMovices3(){
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        movieService.getTopService3(0, 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovicesEntity>() {
            @Override
            public void onCompleted() {
                Log.d("Bill", "::" + Thread.currentThread().toString());
                Log.d("Bill", "onCompleted");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Bill", "::" + Thread.currentThread().toString());
                Log.d("Bill", "error:" + e.getMessage());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onNext(MovicesEntity movicesEntity) {
                Log.d("Bill", "movicesEntity:" + movicesEntity.toString());
            }
        });

    }

    private void getMovices2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovicesEntity> call = movieService.getTopService2(0, 5);
        call.enqueue(new Callback<MovicesEntity>() {
            @Override
            public void onResponse(Call<MovicesEntity> call, Response<MovicesEntity> response) {
                Log.d("Bill", response.body().toString());
            }

            @Override
            public void onFailure(Call<MovicesEntity> call, Throwable t) {
                Log.d("Bill", "error:" + t.getMessage());
            }
        });

    }

    private void getMovices(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<ResponseBody> call = movieService.getTopService(0, 5);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("Bill", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Bill", "error:" + t.getMessage());
            }
        });

    }

}
