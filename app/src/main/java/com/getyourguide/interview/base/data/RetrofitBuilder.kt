package com.getyourguide.interview.base.data

import com.getyourguide.interview.reviews.data.api.ReviewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

  fun <T> provideRetrofit(apiInterface: Class<T>): T =
    Retrofit.Builder()
      .addConverterFactory(MoshiConverterFactory.create())
      .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(Level.BASIC)).build())
      .baseUrl("https://travelers-api.getyourguide.com/")
      .build()
      .create(apiInterface)

  fun provideReviewApis() =  provideRetrofit(ReviewsApi::class.java)
}
