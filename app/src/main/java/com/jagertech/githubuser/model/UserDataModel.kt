package com.jagertech.githubuser.model

import androidx.print.PrintHelper
import com.jagertech.githubuser.api.GithubApi

interface IUserData {
    fun getUser(finishCallback: IResult)

    fun getUsersList(finishCallback: IResult)

    interface IResult{
        fun onResult()
    }
}

class UserDataModel(private val githubApi: GithubApi) : IUserData {
    //model向api請求User個人資料，並將結果回傳回Presenter
    override fun getUser(finishCallback: IUserData.IResult) {
        githubApi.getUserInfo()
        finishCallback.onResult()
    }

    //model向api請求Users清單，並將結果回傳回Presenter
    override fun getUsersList(finishCallback: IUserData.IResult) {
        githubApi.getUsersList()
        finishCallback.onResult()
    }
}

