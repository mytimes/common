package io.github.mytimes;

import io.github.mytimes.Interceptors.LoggingInterceptor;
import io.github.mytimes.events.EventListenerCurrent;
import io.github.mytimes.events.PrintingEventListener;
import okhttp3.*;

import java.security.Security;
import java.time.Duration;

public class SimpleClient {
    private static String url = "https://www.abcde.com/";

    public static void main(String[] args) {

        SimpleClient simpleClient = new SimpleClient();
//        String result = simpleClient.sendGet();
//        System.out.println(result);
        String postResult = simpleClient.sendPost();
        System.out.println(postResult);
    }

    public String sendGet() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().eventListener(new PrintingEventListener()).build();
        Request request = new Request.Builder().url(url)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {

        }
        return "";
    }

    public String sendPost() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
        connectTimeout(Duration.ofSeconds(2)).readTimeout(Duration.ofSeconds(3)).
                eventListenerFactory(EventListenerCurrent.FACTORY).
                addInterceptor(new LoggingInterceptor()).
                build();
        MediaType json = MediaType.parse("application/json;charset=utf-8");
        String body = "{}";
        RequestBody requestBody = RequestBody.create(body, json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try (Response response = okHttpClient.newCall(request).execute();) {
            return response.body().string();
        } catch (Exception e) {

        }
        return "";

    }
}
