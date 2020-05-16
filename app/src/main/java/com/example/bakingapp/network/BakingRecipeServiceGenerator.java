package com.example.bakingapp.network;

import androidx.annotation.NonNull;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BakingRecipeServiceGenerator {

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    @NonNull
    public final <S> S createBakingRecipeApiService(@NonNull final Class<S> serviceClass) {
        return createRetrofit().create(serviceClass);
    }

    @NonNull
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(new OkHttpClient.Builder().build())
                .build();
    }
}