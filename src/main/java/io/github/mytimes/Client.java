package io.github.mytimes;


import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * http的客户端
 */
public class Client {
	private static MediaType type = MediaType.get("application/json; charset=utf-8");
	private static final OkHttpClient httpClient = new OkHttpClient.Builder().
			connectTimeout(2, TimeUnit.SECONDS).writeTimeout(2, TimeUnit.SECONDS).
			readTimeout(2, TimeUnit.SECONDS).build();

	/**
	 * @param url 请求的url
	 * @return 响应的body
	 * @throws IOException 请求发生异常信息
	 */
	public static String get(String url) throws IOException {
		Request request = new Request.Builder().url(url).
				build();
		try (Response response = httpClient.newCall(request).execute()) {
			return result(response);
		}
	}

	/**
	 * @param url 请求的url
	 * @return 响应的body
	 * @throws IOException 请求发生异常信息
	 */
	public static String postJson(String json, String url) throws IOException {
		RequestBody requestBody = RequestBody.create(json, type);
		Request request = new Request.Builder().url(url).post(requestBody).build();
		try (Response response = httpClient.newCall(request).execute()) {
			return result(response);
		}
	}

	private static String result(Response response) throws IOException {
		if (response.isSuccessful()) {
			ResponseBody body = response.body();
			if (body != null) {
				return body.string();
			}
		}
		return null;
	}

}
