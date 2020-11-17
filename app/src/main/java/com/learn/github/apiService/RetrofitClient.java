package com.learn.github.apiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.github.com/";
    private static RetrofitClient instance;
    private static OkHttpClient client;
    private Retrofit retrofit;

    private RetrofitClient() {
        client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


    public static RetrofitClient getApiClient() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiService getApi() {
        return retrofit.create(ApiService.class);
    }


}
