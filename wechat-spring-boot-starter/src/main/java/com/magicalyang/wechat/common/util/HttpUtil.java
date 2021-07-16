package com.magicalyang.wechat.common.util;

import com.magicalyang.wechat.common.message.BaseResp;
import com.magicalyang.wechat.common.exception.WechatErrorException;
import lombok.val;
import okhttp3.*;

import java.io.IOException;

/**
 * @author Constanline
 */
public class HttpUtil {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static <T extends BaseResp> T get(String url, Class<T> cls) throws WechatErrorException {
        val request = new Request.Builder()
                .url(url)
                .build();
        return request(request, cls);
    }

    public static <T extends BaseResp> T post(String url, Object data, Class<T> cls) throws WechatErrorException {
        RequestBody reqBody = RequestBody.create(JSON, JsonUtil.toJson(data));
        val request = new Request.Builder()
                .method("POST", reqBody)
                .url(url)
                .build();

        return request(request, cls);
    }

    private static <T extends BaseResp> T request(Request request, Class<T> cls)
            throws WechatErrorException {
        val client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            val respBody = response.body();
            assert respBody != null;
            val message = JsonUtil.toModel(respBody.string(), cls);
            if (message.getErrCode() != 0) {
                throw new WechatErrorException(message);
            }
            return message;
        } catch (IOException ioException) {
            throw new WechatErrorException(-2, ioException.getMessage());
        }
    }
}
