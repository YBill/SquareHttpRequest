package youyun.com.squarehttprequest.biz;

/**
 * Created by Bill on 2016/7/18.
 */
public interface OkhttpRequest {

    /**
     * 同步get请求
     * @return
     */
    String synchronousGet();

    /**
     * 异步get请求
     * @param listener
     */
    void asynchronousGet(onRequestListener listener);

    /**
     * 同步post请求
     */
    String synchronousPost();

    /**
     * 异步post请求
     * @param listener
     */
    void asynchronousPost(onRequestListener listener);
}
