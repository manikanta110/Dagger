package com.svmexample.dagger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.svmexample.dagger.model.Result;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    List<Result> resultList;
    Context context;

    public MovieAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        Result result = resultList.get(position);
        holder.title.setText(result.getOriginalTitle());

        String image_url = "https://image.tmdb.org/t/p/w500/"+ result.getPosterPath();

       GlideApp.with(context).load(image_url).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;

        public MovieHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.movie_image);
            title = itemView.findViewById(R.id.movie_title);
        }
    }
}
