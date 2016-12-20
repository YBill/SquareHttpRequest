package youyun.com.squarehttprequest.biz;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Bill on 2016/7/18.
 */
public class OkhttpRequestImpl implements OkhttpRequest {

    @Override
    public void httpGet(final onRequestListener listener) {
        // 创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // 创建一个Request
        Request request = new Request.Builder().url("http://www.mocky.io/v2/5185415ba171ea3a00704eed").build();

        // new call
        Call call = okHttpClient.newCall(request);
        // 请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(listener != null)
                    listener.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                if(listener != null)
                    listener.onSuccess(str);
            }
        });

    }

    @Override
    public void httpPost(final onRequestListener listener) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().add("", "").add("", "").build();
        Request request = new Request.Builder().url("http://www.mocky.io/v2/5185415ba171ea3a00704eed").post(body).build();
        // new call
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null)
                    listener.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                if (listener != null)
                    listener.onSuccess(str);
            }
        });
    }
}
