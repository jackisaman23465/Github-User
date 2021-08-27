package com.jagertech.githubuser.presenter

//處理資料邏輯與介面連接關係callback
class UserDataContract {

    //Presenter提供的邏輯處理
    interface IUserDataPresenter{
        fun getUser()

        fun getUsersList()
    }

    //Presenter邏輯處理完與View溝通之callback
    interface IUserView{
        fun changeUI()
    }
}