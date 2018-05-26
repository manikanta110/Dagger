package com.svmexample.dagger;


import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApiModule.class,ApplicationModule.class})
public interface ApiComponent {

    void inject(MainActivity activity);
    Retrofit exposeRetrofit();

}
