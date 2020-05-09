package com.example.newsapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    //http://newsapi.org/v2/everything?q=bitcoin&from=2020-04-06&sortBy=publishedAt&apiKey=8884a4a8e7cd401f839c5c5a285e9adc

    @GET("v2/everything")
    Call<ResponseBody> getNews(@Query("q") String bitcoin, @Query("from") String date, @Query("sortBy")
            String publishedAt, @Query("apiKey") String apikey);
    ///top-headlines
   // http://newsapi.org/v2/top-headlines?country=in&apiKey=8884a4a8e7cd401f839c5c5a285e9adc

   /* @GET("v2/top-headlines")
    Call<ResponseBody>getCurrentNews(@Query("country") String in,@Query("apiKey") String apiKey);*/
    //http://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=API_KEY

    @GET("v2/top-headlines")
    Call<ResponseBody>getCurrentNews(@Query("sources") String google,@Query("apiKey") String apiKey);
}


