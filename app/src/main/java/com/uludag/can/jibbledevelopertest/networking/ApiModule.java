package com.uludag.can.jibbledevelopertest.networking;

import com.uludag.can.jibbledevelopertest.BuildConfig;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    OkHttpClient provideClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // Level can be set to BODY for more detailed logging
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    Retrofit provideRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    ApiService provideApiService() {
        return provideRetrofit(BuildConfig.BASE_API_URL, provideClient())
                .create(ApiService.class);
    }
}
