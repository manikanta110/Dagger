package com.svmexample.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.svmexample.dagger.Network.Service;
import com.svmexample.dagger.model.Result;
import com.svmexample.dagger.model.TopMovie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";


 RecyclerView recyclerView;
 MovieAdapter adapter;
 List<Result> results;




    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication)getApplication()).getApplication().getApiComponent().inject(this);


        recyclerView =  findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);





        getMovies();




    }

    private void getMovies() {
        Retrofit retrofit = ((MyApplication)getApplication()).getApiComponent().exposeRetrofit();
        Service service = retrofit.create(Service.class);



        Call<TopMovie> call = service.getpopularmovies("81c4047a8486904dd6cf0787b4b47dc9");
        call.enqueue(new Callback<TopMovie>() {
            @Override
            public void onResponse(Call<TopMovie> call, Response<TopMovie> response) {


                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.body().getResults());

                    results = response.body().getResults();


                    adapter= new MovieAdapter(results,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }










            }

            @Override
            public void onFailure(Call<TopMovie> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Occured"+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: "+t.getMessage());

            }
        });
    }




}
