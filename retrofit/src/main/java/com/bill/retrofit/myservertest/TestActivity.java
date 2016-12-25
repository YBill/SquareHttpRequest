package com.bill.retrofit.myservertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bill.retrofit.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testDelete();
            }
        });
    }

    private void testPut(){
        Retrofit retrofit = getRetrofit2();
        MyInterfaceApi api = retrofit.create(MyInterfaceApi.class);
        LanguageEntity entity = new LanguageEntity();
        entity.name = "法语";
        Call<ResponseBody> call = api.putNovelParams("5", entity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.d("Bill", "result:" + result);
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

    private void testDelete(){
        Retrofit retrofit = getRetrofit();
        MyInterfaceApi api = retrofit.create(MyInterfaceApi.class);
        Call<ResponseBody> call = api.deleteNovelById("3");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.d("Bill", "result:" + result);
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

    private void testPost(){
//        Retrofit retrofit = getRetrofit();
        Retrofit retrofit = getRetrofit2(); // post body 时必须addConverterFactory(GsonConverterFactory.create())
        MyInterfaceApi api = retrofit.create(MyInterfaceApi.class);
        LanguageEntity entity = new LanguageEntity();
        entity.id = "5";
        entity.name = "俄语";
        Call<ResponseBody> call = api.postNovelEntity(entity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.d("Bill", "result:" + result);
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

    private void testGet2(){
        Retrofit retrofit = getRetrofit2();
        MyInterfaceApi api = retrofit.create(MyInterfaceApi.class);
        Call<List<NovelEntity>> call = api.getNovels2();
        call.enqueue(new Callback<List<NovelEntity>>() {
            @Override
            public void onResponse(Call<List<NovelEntity>> call, Response<List<NovelEntity>> response) {
                String result = response.body().toString();
                Log.d("Bill", "result:" + result);
            }

            @Override
            public void onFailure(Call<List<NovelEntity>> call, Throwable t) {
                Log.d("Bill", "error:" + t.getMessage());
            }
        });

    }

    private void testGet(){
        Retrofit retrofit = getRetrofit();
        MyInterfaceApi api = retrofit.create(MyInterfaceApi.class);
        Call<ResponseBody> call = api.getNovels();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.d("Bill", "result:" + result);
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

    private Retrofit getRetrofit2(){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.2.111:3000/")
                .addConverterFactory(GsonConverterFactory.create()) // json和实体类相互转换时用到
                .build();
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.2.111:3000/")
                .build();
    }

}
