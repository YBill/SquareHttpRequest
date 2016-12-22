package youyun.com.squarehttprequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import youyun.com.squarehttprequest.biz.OkhttpRequest;
import youyun.com.squarehttprequest.biz.OkhttpRequestImpl;
import youyun.com.squarehttprequest.biz.onRequestListener;

public class MainActivity extends AppCompatActivity {

    private OkhttpRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request = new OkhttpRequestImpl();

    }

    public void handlerGet(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = request.synchronousGet();
                Log.v("Bill", "同步get请求:" + result);
            }
        }).start();


        request.asynchronousGet(new onRequestListener() {
            @Override
            public void onSuccess(String response) {
                Log.v("Bill", "异步get请求:" + response);
            }

            @Override
            public void onError(String error) {
                Log.v("Bill", "error:" + error);
            }
        });
    }

    public void handlerPost(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = request.synchronousPost();
                Log.v("Bill", "同步post请求:" + result);
            }
        }).start();

        request.asynchronousPost(new onRequestListener() {
            @Override
            public void onSuccess(String response) {
                Log.v("Bill", "异步post请求:" + response);
            }

            @Override
            public void onError(String error) {
                Log.v("Bill", "error:" + error);
            }
        });
    }

}
