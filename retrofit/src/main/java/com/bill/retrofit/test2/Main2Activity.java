package com.bill.retrofit.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bill.retrofit.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testPost();
            }
        });
    }

    public interface GroupApi{
        @Headers({
                    "Authorization: MAuth 030766aae1c8bccdb21fcd498ab3a87368ce29cb1a1cedf5b976d24b9b53c7bb",
                    "X-WVersion: 1.0.9-1.0.2.1-964e3f6d6b7de150-M356-weibo",
                    "X-Client-ID: 1-20521-1b766ad17389c94e1dc1f2615714212a-andriod"
                })
        @GET("group/list")
        Call<ResponseBody> getGroupList(@QueryMap Map<String, String> map);

        @Headers({
                "Authorization: MAuth 030766aae1c8bccdb21fcd498ab3a87368ce29cb1a1cedf5b976d24b9b53c7bb",
                "X-WVersion: 1.0.9-1.0.2.1-964e3f6d6b7de150-M356-weibo",
                "X-Client-ID: 1-20521-1b766ad17389c94e1dc1f2615714212a-andriod"
        })
        @FormUrlEncoded
        @POST("group/create")
        Call<ResponseBody> createGroup(@FieldMap Map<String, Object> map);
    }

    private void testPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ioyouyun.com/")
                .build();
        GroupApi groupApi = retrofit.create(GroupApi.class);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "天王盖地虎");
        map.put("intra", "retrofit 测试");
        Call<ResponseBody> call = groupApi.createGroup(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("Bill", "成功：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Bill", "成功：" + t.getMessage());
            }
        });

    }

    private void testGet(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ioyouyun.com/")
                .build();
        GroupApi groupApi = retrofit.create(GroupApi.class);

        Map<String, String> map = new HashMap<>();
        map.put("uid", "271576");
        map.put("showtype", "2");
        map.put("cat1", "0,2,3");
        map.put("cat2", "0");
        map.put("cat3", "0");
        Call<ResponseBody> call = groupApi.getGroupList(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("Bill", "成功：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Bill", "成功：" + t.getMessage());
            }
        });

    }

}
