package com.example.newsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsDetailspass {
    private RecyclerView mRvNewsList;
    public static final String TAG = MainActivity.class.getSimpleName();
    private String Apikey = "8884a4a8e7cd401f839c5c5a285e9adc";
    //8884a4a8e7cd401f839c5c5a285e9adc
    //8884a4a8e7cd401f839c5c5a285e9adc
    private ArrayList<Articles> articlesArrayList;
    private Articles articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void getNews(String formattedDate) {
        APIInterface apiClient = APIClient.getClient().create(APIInterface.class);
        Call<ResponseBody> call = apiClient.getNews("bitcoin", formattedDate, "publishedAt", Apikey);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.d(TAG, "onResponse:dta  ::: " + jsonObject.toString());
                        String status = jsonObject.optString("status");
                        if (status.equalsIgnoreCase("ok")) {
                            JSONArray jsonArray = jsonObject.optJSONArray("articles");
                            Log.d(TAG, "onResponse: articles" + jsonArray + toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                JSONObject jsonObject2 = jsonObject1.optJSONObject("source");
                                articles = new Articles();
                                articles.sourceName = Objects.requireNonNull(jsonObject2).optString("name");
                                articles.author = jsonObject1.optString("author");
                                articles.title = jsonObject1.optString("title");
                                articles.description = jsonObject1.optString("description");
                                articles.urlToImage = jsonObject1.optString("urlToImage");
                                articles.publishedAt = jsonObject1.optString("publishedAt");
                                articles.content = jsonObject1.optString("content");
                                articlesArrayList.add(articles);

                            }

                            Log.d(TAG, "onResponse: list time" + articles.publishedAt);


                            setAdapter(articlesArrayList);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "onResponse:dta fail ::: " + response.toString());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });

    }

    private void setAdapter(ArrayList<Articles> articlesArrayList) {
        NewsAdapter newsAdapter = new NewsAdapter(this, articlesArrayList);
        mRvNewsList.setAdapter(newsAdapter);
        newsAdapter.setListner(this::newsDetails);

    }

    private void init() {
        articlesArrayList = new ArrayList<>();

        mRvNewsList = findViewById(R.id.recylerview);
        mRvNewsList.setLayoutManager(new LinearLayoutManager(this));
        getCurrentNews();

    }



    public void getCurrentNews() {
        APIInterface apiClient = APIClient.getClient().create(APIInterface.class);
        Call<ResponseBody> call = apiClient.getCurrentNews("google-news-in", Apikey);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.d(TAG, "onResponse:dta  ::: " + jsonObject.toString());
                        String status = jsonObject.optString("status");
                        if (status.equalsIgnoreCase("ok")) {
                            JSONArray jsonArray = jsonObject.optJSONArray("articles");
                            Log.d(TAG, "onResponse: articles" + jsonArray + toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                JSONObject jsonObject2 = jsonObject1.optJSONObject("source");
                                articles = new Articles();
                                articles.sourceName = jsonObject2.optString("name");
                                articles.author = jsonObject1.optString("author");
                                articles.title = jsonObject1.optString("title");
                                articles.description = jsonObject1.optString("description");
                                articles.urlToImage = jsonObject1.optString("urlToImage");
                                articles.url = jsonObject1.optString("url");
                                articles.publishedAt = jsonObject1.optString("publishedAt");
                                articles.content = jsonObject1.optString("content");
                                articlesArrayList.add(articles);

                            }

                            Log.d(TAG, "onResponse: list" + articlesArrayList.size());


                            setAdapter(articlesArrayList);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "onResponse:dta fail ::: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }

        });
    }

    @Override
    public void newsDetails(Articles articles) {

        Intent intent = new Intent(this, ScrollingActivity.class);
        intent.putExtra("details", (Serializable) articles);
        startActivity(intent);


    }
}

