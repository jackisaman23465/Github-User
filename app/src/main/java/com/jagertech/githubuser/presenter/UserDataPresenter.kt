package com.jagertech.githubuser.presenter

import com.jagertech.githubuser.model.IUserData
import com.jagertech.githubuser.model.UserDataModel

class UserDataPresenter(
    private val view: UserDataContract.IUserView,
    private val model: UserDataModel
):UserDataContract.IUserDataPresenter{

    //向model請求獲取User個人資料
    override fun getUser() {
        model.getUser(object:IUserData.IResult{
            override fun onResult() {
                view.changeUI()
            }
        })
    }

    //向model請求獲取Users清單
    override fun getUsersList() {
        model.getUsersList(object:IUserData.IResult{
            override fun onResult() {
                view.changeUI()
            }
        })
    }
}