package com.example.newsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private ArrayList<Articles> articlesArrayList;
    private NewsDetailspass listner;

    public NewsAdapter(Context context, ArrayList<Articles> articlesArrayList) {
        this.context = context;
        this.articlesArrayList = articlesArrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_news_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.mTvnewsHeading.setText(articles.getTitle());
        holder.mTvnewsDescription.setText(articles.getDescription());
        holder.mTvnewsSource.setText(articles.getSourceName());
        Glide.with(context).load(articles.getUrlToImage()).into(holder.mIvNewsImage);
       /* holder.mTvnewsHeading.setText(articles.getTitle());
        holder.mTvnewsHeading.setText(articles.getTitle());
        holder.mTvnewsHeading.setText(articles.getTitle());*/

        holder.mCvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.newsDetails(articles);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView mTvnewsHeading;
        TextView mTvnewsDescription;
        TextView mTvnewsSource;
        TextView mTvnewsWriter;
        TextView mTvnewsDate;
        ImageView mIvNewsImage;
        CardView mCvParent;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvnewsHeading = itemView.findViewById(R.id.tv_heading);
            mTvnewsDescription = itemView.findViewById(R.id.description);
            mTvnewsSource = itemView.findViewById(R.id.tv_source);
            mTvnewsWriter = itemView.findViewById(R.id.myImageViewText);
            mTvnewsDate = itemView.findViewById(R.id.date);
            mIvNewsImage = itemView.findViewById(R.id.iv_news);
            mCvParent = itemView.findViewById(R.id.cv_parent);


        }
    }

    public void setListner(NewsDetailspass listner) {
        this.listner = listner;
    }

    public interface NewsDetailspass {
        void newsDetails(Articles articles);
    }

}
