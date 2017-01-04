package com.bill.retrofit.rxjava;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Bill on 2017/1/4.
 */

public interface MovieService {

    @GET("top250")
    Call<ResponseBody> getTopService(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Call<MovicesEntity> getTopService2(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<MovicesEntity> getTopService3(@Query("start") int start, @Query("count") int count);

}
