package com.jagertech.githubuser.model

import android.util.Log
import androidx.print.PrintHelper
import com.jagertech.githubuser.api.DataFormat.UserDataFormat
import com.jagertech.githubuser.api.GithubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface IUserData {
    fun getUser(finishCallback: IUserResult,userName: String)

    fun getUsersList(finishCallback: IUsersListResult)

    interface IUsersListResult{
        fun onUsersListResult(result: List<UserDataFormat>)
    }

    interface IUserResult{
        fun onUserResult(result: UserDataFormat)
    }
}

class UserDataModel(private val githubApi: GithubApi) : IUserData {
    //model向api請求User個人資料，並將結果回傳回Presenter
    override fun getUser(finishCallback: IUserData.IUserResult,userName:String) {
        val response = githubApi.getUserInfo(userName)
        response.enqueue(object: Callback<UserDataFormat> {
            override fun onFailure(call: Call<UserDataFormat>, t: Throwable) {
                println("1")
            }

            override fun onResponse(
                call: Call<UserDataFormat>,
                response: Response<UserDataFormat>
            ) {
                response.body()?.let { finishCallback.onUserResult(it) }
            }

        })
    }

    //model向api請求Users清單，並將結果回傳回Presenter
    override fun getUsersList(finishCallback: IUserData.IUsersListResult) {
        val response = githubApi.getUsersList()
        response.enqueue(object: Callback<List<UserDataFormat>> {
            override fun onFailure(call: Call<List<UserDataFormat>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<UserDataFormat>>,
                response: Response<List<UserDataFormat>>
            ) {
                response.body()?.let { finishCallback.onUsersListResult(it) }
            }

        })
    }
}

