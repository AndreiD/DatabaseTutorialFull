package com.androidadvance.databaseone.data.remote;

import android.content.Context;
import com.androidadvance.databaseone.BuildConfig;
import com.androidadvance.databaseone.model.News;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheAPI {

  String API_KEY = "57e2864e3dfe499e8394a29ffac9626d";

  String BASE_URL = "http://api.nytimes.com/svc/";

  @GET("search/v2/articlesearch.json?&page=2&sort=oldest&api-key=" + API_KEY) Call<News> getNews(@Query("q") String query);

  class Factory {
    private static TheAPI service;

    public static TheAPI getIstance(Context context) {
      if (service == null) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
          HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
          interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
          builder.addInterceptor(interceptor);
        }

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        builder.cache(cache);

        Retrofit retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        service = retrofit.create(TheAPI.class);
        return service;
      } else {
        return service;
      }
    }
  }
}


