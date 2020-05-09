package com.example.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {
    private ImageView mIvCollapsingImage;
    private Articles articles;
    private TextView mTvHeading;
    private TextView mTvSourceName;
    private TextView mTvHoursAgo;
    public static final String TAG = DetailsNewsActivity.class.getSimpleName();
    WebView browser;
    private Context context;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        init();
        collpsing();
    }

    private void init() {
        date = (TextView) findViewById(R.id.date);
        mIvCollapsingImage = findViewById(R.id.iv_heading_news);
        browser = (WebView) findViewById(R.id.news_Details);
         mTvHeading = findViewById(R.id.tv_news_heading);
        mTvSourceName = findViewById(R.id.tv_source_name);
        mTvHoursAgo = findViewById(R.id.hours_ago);
        Intent i = getIntent();
        articles = (Articles) i.getSerializableExtra("details");
        Glide.with(this).load(articles.urlToImage).into(mIvCollapsingImage);
        browser.loadUrl(articles.url);
        Log.d(TAG, "init: url:::" + articles.url);
        // mTvHeading.setText(articles.content);
        String formateddate = Utill.getDate(articles.getPublishedAt());
        date.setText(formateddate);
        Log.d(TAG, "init: date::1" + articles.publishedAt);
        String timeAgo = TimeAgo.covertTimeToText((articles.getPublishedAt()));
        mTvSourceName.setText(articles.sourceName);
        mTvHoursAgo.setText(timeAgo);
        mTvHeading.setText(articles.title);

        browser.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });


    }

    private void collpsing() {
        this.context = this;
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(articles.getContent());
                    Glide.with(context).load("").into(mIvCollapsingImage);
                    // mTvHeading.setText(articles.content);
                    date.setVisibility(View.GONE);

                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    Glide.with(context).load(articles.urlToImage).into(mIvCollapsingImage);
                    //mTvHeading.setText(articles.content);
                    String formateddate = Utill.getDate(articles.getPublishedAt());
                    date.setText(formateddate);
                    date.setVisibility(View.VISIBLE);

                    isShow = false;
                }
            }
        });
    }
}
