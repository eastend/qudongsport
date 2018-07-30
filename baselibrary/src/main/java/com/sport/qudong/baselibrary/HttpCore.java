package com.sport.qudong.baselibrary;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wood on 2018/3/5.
 */

public class HttpCore {

    private static final String TAG = "HttpCore";
    private static final int defaultTimeOut = 10;
    private static String baseUrl = "";
    private static boolean showLog = false;


    public static final void init(String baseUrl, boolean showLog) {
        HttpCore.baseUrl = baseUrl;
        HttpCore.showLog = showLog;
    }


    public static final OkHttpClient getHttpClient(int connectTimeout, int readTimeout, int writeTimeout, boolean showLog, Interceptor... interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS);
        if (interceptor != null) {
            for (int i = 0; i < interceptor.length; i++) {
                builder.addInterceptor(interceptor[i]);
            }
        }

        if (showLog) {
            builder.addInterceptor(new LogInterceptor());
        }
        OkHttpClient httpClient = builder.build();
        return httpClient;
    }

    /**
     * 默认的创建serivce
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass, Interceptor... interceptor) {
        return createService(serviceClass, defaultTimeOut, defaultTimeOut, defaultTimeOut, showLog, interceptor);
    }

    /**
     * 带时间的创建service
     *
     * @param serviceClass
     * @param connectTimeout
     * @param readTimeout
     * @param writeTimeout
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass, int connectTimeout, int readTimeout, int writeTimeout, boolean showLog, Interceptor... interceptor) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(getBaseUrl())
                        .client(getHttpClient(connectTimeout, readTimeout, writeTimeout, showLog, interceptor))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        return retrofit.create(serviceClass);
    }


    public static <S> S createDownLoadService(Class<S> serviceClass, Interceptor... interceptor) {
        return createService(serviceClass, defaultTimeOut, defaultTimeOut, defaultTimeOut, false, interceptor);
    }


    public static <S> S createService(Class<S> serviceClass, int defaultTimeOut, Interceptor... interceptor) {
        return createService(serviceClass, defaultTimeOut, defaultTimeOut, defaultTimeOut, showLog, interceptor);
    }


    public static final String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 日志拦截器
     */
    private static class LogInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            log(TAG, "request:" + request.toString() + "\nrequestHead:" + request.headers());
            long t1 = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
//           log(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            if (response.body() != null) {
                log(TAG, "response body:" + content);
            } else {
                log(TAG, "response body==null");
            }
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    }

    public static void log(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.d(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.d(tag, msg);
    }

}
