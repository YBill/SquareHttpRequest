package youyun.com.squarehttprequest.biz;

/**
 * Created by Bill on 2016/7/18.
 */
public interface onRequestListener {

    /**
     * http请求回调
     * @param response
     */
    void onSuccess(String response);

    /**
     * 错误回调
     * @param error
     */
    void onError(String error);

}
