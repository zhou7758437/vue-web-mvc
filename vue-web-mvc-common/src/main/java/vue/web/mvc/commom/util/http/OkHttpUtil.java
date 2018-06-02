package vue.web.mvc.commom.util.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import okhttp3.*;

import java.io.IOException;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/11 15:48
 * Description:
 */
public class OkHttpUtil {

    public static final MediaType JSON_TYPE
            = MediaType.parse("application/json; charset=utf-8");

    public static byte[] getBytes(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        byte[] json = response.body().bytes();
        return json;
    }
    public static String get(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String json = response.body().string();
        return json;
    }

    public static <T> T get(String url, Class<T> tClass) throws IOException {

        String json = get(url);
        return JSON.parseObject(json, tClass, Feature.IgnoreNotMatch);
    }

    public static String post(String url, String params) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body=RequestBody.create(JSON_TYPE,params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String json = response.body().string();
        return json;
    }

    public static byte[] postBytes(String url, String params) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body=RequestBody.create(JSON_TYPE,params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        byte[] json = response.body().bytes();
        return json;
    }
}
