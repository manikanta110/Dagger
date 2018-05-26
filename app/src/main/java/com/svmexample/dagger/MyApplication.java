package com.svmexample.dagger;

import android.app.Application;

public class MyApplication extends Application {
    private  MyApplication application;


     ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

         application = this;

         initComponent();




    }

    private void initComponent() {

        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule("https://api.themoviedb.org/3/"))
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }

    public MyApplication getApplication() {
        return application;
    }
}
