package com.bill.retrofit.myservertest;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Bill on 2016/12/25.
 */

public interface MyInterfaceApi {

    /////////////////////////////////////////////////////GET//////////////////////////////////////////

    /**
     * http://192.168.2.111:3000/novel
     * 获取全部小说
     * @return json
     */
    @GET("novel")
    Call<ResponseBody> getNovels();

    /**
     * http://192.168.2.111:3000/novel
     * 获取全部小说
     * @return NovelEntity
     */
    @GET("novel")
    Call<List<NovelEntity>> getNovels2();

    /**
     * http://192.168.2.111:3000/novel
     * 获取全部小说
     * @return
     */
    @GET("")
    Call<ResponseBody> getNovelsWithUrl(@Url String url);

    /**
     * http://192.168.2.111:3000/novel/x
     * 根据id获取小说
     * @return
     */
    @GET("novel/{id}")
    Call<ResponseBody> getNovelById(@Path("id")String id);

    /**
     *  http://192.168.2.111:3000/novel?id=x
     * 根据id获取小说
     * @return
     */
    @GET("novel")
    Call<ResponseBody> getNovelById2(@Query("id") String id);

    /**
     * http://192.168.2.111:3000/novel/x
     * 根据id获取小说
     * @return Entity
     */
    @GET("novel/{id}")
    Call<NovelEntity> getNovelById3(@Path("id")String id);

    /**
     *  http://192.168.2.111:3000/novel?id=x&name="x"
     * 根据id name获取小说
     * @return
     */
    @GET("novel/") // novel带不带"/"都可以
    Call<ResponseBody> getNovelByIdName(@Query("id") int id, @Query("name") String name);

    /**
     *  http://192.168.2.111:3000/novel?key=value&key=value... map的key为接口的参数value为值,接口参数没有则参数无效
     * 根据id name获取小说
     * @return
     */
    @GET("novel")
    Call<ResponseBody> getNovelByParams(@QueryMap Map<String, String> map);

    /**
     * http://192.168.2.111:3000/xxx/x
     * 根据id获取小说
     * @return
     */
    @GET("{method}/{id}")
    Call<ResponseBody> getNovelsWithMethodByid(@Path("method")String method, @Path("id")String id);

    /**
     * http://192.168.2.111:3000/xxx?id=x
     * 根据id获取小说
     * @return
     */
    @GET("{method}")
    Call<ResponseBody> getNovelsWithMethodByid2(@Path("method")String method, @Query("id") String id);


    /////////////////////////////////////////////////////POST//////////////////////////////////////////

    /**
     * http://192.168.2.111:3000/language
     * 插入一条数据
     * @param id
     * @param name
     * @return
     */
    @FormUrlEncoded
    @POST("language")
    Call<ResponseBody> postNovelParams(@Field("id") String id, @Field("name") String name);

    /**
     * http://192.168.2.111:3000/language
     * 插入一条数据,path和query和get的规则一样
     * @param id
     * @param name
     * @return
     */
    @FormUrlEncoded
    @POST("{method}")
    Call<ResponseBody> postNovelParams2(@Path("method") String method, @Field("id") String id, @Field("name") String name);

    /**
     * http://192.168.2.111:3000/language
     * 插入一条数据 对象
     * @param languageEntity
     * @return
     */
    @POST("language")
    Call<ResponseBody> postNovelEntity(@Body LanguageEntity languageEntity);



    /////////////////////////////////////////////////////DELETE//////////////////////////////////////////

    /**
     * http://192.168.2.111:3000/language
     * 删除一条数据
     * @param id
     * @return
     */
    @DELETE("language/{id}")
    Call<ResponseBody> deleteNovelById(@Path("id") String id);

    /**
     * http://192.168.2.111:3000/language
     * 删除一条数据
     * @param id
     * @return
     */
    @Deprecated   // 这种写法会报错
    @DELETE("language")
    Call<ResponseBody> deleteNovelById2(@Query("id") int id);



    /////////////////////////////////////////////////////PUT//////////////////////////////////////////

    /**
     * http://192.168.2.111:3000/language
     * 修改一条数据
     * @param id
     * @param languageEntity
     * @return
     */
    @PUT("language/{id}")
    Call<ResponseBody> putNovelParams(@Path("id") String id, @Body LanguageEntity languageEntity);

}
