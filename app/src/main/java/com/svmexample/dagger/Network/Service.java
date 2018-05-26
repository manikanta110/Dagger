package com.svmexample.dagger.Network;

import com.svmexample.dagger.model.TopMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/upcoming")
    Call<TopMovie> getpopularmovies(@Query("api_key") String api);
}
