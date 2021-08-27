package com.jagertech.githubuser.api

import com.jagertech.githubuser.api.DataFormat.UserDataFormat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi{

    @GET("users")
    fun getUsersList():Call<List<UserDataFormat>>

    @GET("users/{username}")
    fun getUserInfo(@Path("username") username: String):Call<UserDataFormat>
}