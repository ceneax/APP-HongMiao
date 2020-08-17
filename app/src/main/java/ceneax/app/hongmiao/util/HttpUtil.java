package ceneax.app.hongmiao.util;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import ceneax.app.hongmiao.App;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    // 单例对象
    private static HttpUtil instance;

    private OkHttpClient okHttpClient;

    // 主线程对象
    private Handler mainThreadHandler;

    /**
     * 获取单例
     * @return 返回实例
     */
    public static HttpUtil getInstance() {
        if(instance == null)
            return new HttpUtil();
        return instance;
    }

    /**
     * 构造函数
     */
    public HttpUtil() {
        okHttpClient = new OkHttpClient();
        mainThreadHandler = new Handler(App.getAppContext().getMainLooper());
    }

    /**
     * get请求，不带header
     * @param url url
     * @param listener 回调接口
     */
    public void get(String url, final Listener listener) {
        get(url, new Headers.Builder().build(), listener);
    }

    /**
     * get请求，带header
     * @param url url
     * @param headers header
     * @param listener 回调接口
     */
    public void get(String url, Headers headers, final Listener listener) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            listener.success(response);
                        } catch (IOException e) {
                            listener.fail(e);
                        }
                    }
                });
            }
        });
    }

    /**
     * post请求，不带header和contentType
     * @param url url
     * @param body body String
     * @param listener 回调接口
     */
    public void post(String url, String body, Listener listener) {
        post(url, new Headers.Builder().build(), body, listener);
    }

    /**
     * post请求，不带contentType
     * @param url url
     * @param headers header
     * @param body body String
     * @param listener 回调接口
     */
    public void post(String url, Headers headers, String body, Listener listener) {
        post(url, headers, body, "application/json;charset=utf-8", listener);
    }

    /**
     * post请求，不带header
     * @param url url
     * @param body body String
     * @param contentType contentType
     * @param listener 回调接口
     */
    public void post(String url, String body, String contentType, Listener listener) {
        post(url, new Headers.Builder().build(), body, contentType, listener);
    }

    /**
     * post请求，带header和contentType
     * @param url url
     * @param headers header
     * @param body body String
     * @param contentType contentType
     * @param listener 回调接口
     */
    public void post(String url, Headers headers, String body, String contentType, final Listener listener) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(RequestBody.create(body, MediaType.parse(contentType)))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            listener.success(response);
                        } catch (IOException e) {
                            listener.fail(e);
                        }
                    }
                });
            }
        });
    }

    /**
     * post请求，不带header
     * @param url url
     * @param formBody formBody
     * @param listener 回调接口
     */
    public void post(String url, FormBody formBody, final Listener listener) {
        post(url, new Headers.Builder().build(), formBody, listener);
    }

    /**
     * post请求，带header
     * @param url url
     * @param headers header
     * @param formBody formBody
     * @param listener 回调接口
     */
    public void post(String url, Headers headers, FormBody formBody, final Listener listener) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            listener.success(response);
                        } catch (IOException e) {
                            listener.fail(e);
                        }
                    }
                });
            }
        });
    }

    /**
     * 回调接口
     */
    public interface Listener {
        void success(Response response) throws IOException;
        void fail(IOException e);
    }

}
