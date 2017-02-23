package com.kaiba.demo.okhttp;

/**
 * Created by luliru on 2017/2/23.
 */

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpContributors {

    public static void main(String... args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        // Create request for remote resource.
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();

        // Execute the request and retrieve the response.
        Response response = client.newCall(request).execute();

        // Deserialize HTTP response to concrete type.
        ResponseBody body = response.body();
        System.out.println(body.string());

        body.close();
    }
}
