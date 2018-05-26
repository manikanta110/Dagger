package com.svmexample.dagger;


import android.app.Application;

import com.svmexample.dagger.Network.Service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    String Base_url;

    public ApiModule(String base_url) {
        Base_url = base_url;
    }

    @Provides
    @Singleton
    Retrofit getRetrofit(GsonConverterFactory factory, OkHttpClient client){
       return new Retrofit.Builder().baseUrl(Base_url).addConverterFactory(factory).client(client).build();
    }

    @Provides
    @Singleton
    OkHttpClient getOkhttpClient(Cache cache){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).cache(cache).build();
    }


    @Provides
    @Singleton
    Cache ProvideCache(Application application){

        int cachesize = 10*1024*1024;
        Cache cache = new Cache(application.getCacheDir(),cachesize);
        return cache;
    }

    @Provides
    @Singleton
    GsonConverterFactory providefactory(){
        return GsonConverterFactory.create();
    }


}
