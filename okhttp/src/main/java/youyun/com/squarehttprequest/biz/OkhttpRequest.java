package youyun.com.squarehttprequest.biz;

/**
 * Created by Bill on 2016/7/18.
 */
public interface OkhttpRequest {

    /**
     * get请求
     * @param listener
     */
    void httpGet(onRequestListener listener);

    /**
     * post请求
     * @param listener
     */
    void httpPost(onRequestListener listener);
}
