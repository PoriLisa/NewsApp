package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsNewsActivity extends AppCompatActivity {

    private ImageView mIvCollapsingImage;
    private Articles articles;
    private TextView mTvHeading;
    public static final String TAG = DetailsNewsActivity.class.getSimpleName();
    WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_news);
        init();
    }

    private void init() {
        mIvCollapsingImage = findViewById(R.id.iv_heading_news);
        browser = (WebView) findViewById(R.id.news_Details);
        mTvHeading = findViewById(R.id.tv_news_heading);
        Intent i = getIntent();
        articles = (Articles) i.getSerializableExtra("details");
        Glide.with(this).load(articles.urlToImage).into(mIvCollapsingImage);
        browser.loadUrl(articles.url);
        Log.d(TAG, "init: url:::"+articles.url);
        mTvHeading.setText(articles.title);
    }
}
