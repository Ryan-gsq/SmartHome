package bbu.com.smartoffice.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/28.
 */

public class HttpRequest {
    private static final String TAG = "HttpRequest";

    private static HttpRequest httpRequest;


    public static HttpRequest getInstance() {
        if (httpRequest == null) {
            synchronized (HttpRequest.class) {
                if (httpRequest == null) {
                    httpRequest = new HttpRequest();
                }
            }
        }
        return httpRequest;
    }

    private void get(String url, final Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder().
                addHeader("content-encoding", "gzip").
                addHeader("content-type", "charset=UTF-8").
                addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").
                // addHeader("user-agent", "Mozilla/5.0 (Linux; Android) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166  Safari/535.19").
                        url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(call, response);
            }
        });
    }

    public void queryDevice(String id, final Callback callback) throws IOException {
        String url = "http://api.heclouds.com/devices/" + id;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().
                addHeader("api-key", "nQQC7mWauJvWKSzImNg4tmNf03M=").
               // addHeader("content-type", "charset=UTF-8").
              //  addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").
                url(url).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(call, response);
            }
        });
    }

//    enum RequsetMethod  {
//        GET("0"),
//        POST("1");
//    }
}
