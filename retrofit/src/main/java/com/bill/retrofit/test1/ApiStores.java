package com.bill.retrofit.test1;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bill on 2016/12/24.
 */

public interface ApiStores {

    /**
     * get请求 http://www.weather.com.cn/adat/sk/xxxxx.html
     * 注：Call<T> get()必须是这种形式,这是2.0之后的新形式，否则会出现ArrayIndexOutOfBoundsException错误
     * @param cityId
     * @return
     */
    @GET("adat/sk/{cityId}.html")
    Call<ResponseBody> getWeather(@Path("cityId") String cityId);

    /**
     * 通过gson将json字符串转换额为实体类
     * @param cityId
     * @return
     */
    @GET("adat/sk/{cityId}.html")
    Call<WeatherEntity> getWeatherT(@Path("cityId") String cityId);

    /**
     * get请求带?的形式  http://ip.taobao.com/service/getIpInfo.php?ip=202.202.33.33
     *
     * @return
     */
    @GET("http://ip.taobao.com/service/getIpInfo.php")
    Call<ResponseBody> getIpInfo(@Query("ip") String ip);

}
