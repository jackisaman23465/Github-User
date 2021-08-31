package com.jagertech.githubuser.presenter

import com.jagertech.githubuser.api.DataFormat.UserDataFormat
import retrofit2.Response

//處理資料邏輯與介面連接關係callback
class UserDataContract {

    //Presenter提供的邏輯處理
    interface IUserDataPresenter{
        fun getUser(userName:String)

        fun getUsersList()
    }

    //Presenter邏輯處理完與View溝通之callback
    interface IView{
        fun changeUsersListUI(result: List<UserDataFormat>)
        fun changeUserUI(result: UserDataFormat)
    }
}