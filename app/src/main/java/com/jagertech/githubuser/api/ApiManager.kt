package com.jagertech.githubuser.api

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jagertech.githubuser.api.DataFormat.UserDataFormat
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT:Long = 50

class ApiManager {

    val builder = OkHttpClient.Builder()
    var retrofit : Retrofit
    var githubApi :GithubApi

    init{

        builder
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader(
                    "Authorization",
                    "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"
                )
                chain.proceed(requestBuilder.build())
            }

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setLenient()
                .create()))
            .build()

        githubApi = retrofit.create(GithubApi::class.java)
    }
}