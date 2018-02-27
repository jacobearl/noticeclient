package com.ailu.paas;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.security.DigestException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NetWorkUtils {
    private static final OkHttpClient okHttpClient;

    public NetWorkUtils() {
    }

    public static JSONObject httpPost(String requestUrl, String appKey, String secret, Map<String, Object> paramMap) throws DigestException {
        JSONObject param = assembleParam(appKey, secret, paramMap);
        String result = sendPost(requestUrl, param);
        if (null != result) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            return jsonObject;
        } else {
            return null;
        }
    }

    private static String sendPost(String requestUrl, JSONObject param) {
        MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonType, param.toJSONString());
        Request request = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(requestUrl).post(body).build();
        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException var7) {
            var7.printStackTrace();
            return null;
        }
    }

    private static JSONObject assembleParam(String appKey, String secret, Map<String, Object> paramMap) throws DigestException {
        JSONObject param = new JSONObject();
        param.put("paramMap", paramMap);
        paramMap.put("secret", secret);
        String signature = Signature.signature(paramMap);
        param.put("appKey", appKey);
        paramMap.remove("secret");
        param.put("signature", signature);
        return param;
    }

    static {
        okHttpClient = (new okhttp3.OkHttpClient.Builder()).connectTimeout(30L, TimeUnit.SECONDS).build();
    }
}