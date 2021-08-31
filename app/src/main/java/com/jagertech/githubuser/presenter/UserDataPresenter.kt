package com.jagertech.githubuser.presenter

import com.jagertech.githubuser.api.DataFormat.UserDataFormat
import com.jagertech.githubuser.model.IUserData
import com.jagertech.githubuser.model.UserDataModel
import retrofit2.Response

class UserDataPresenter(
    private val view: UserDataContract.IView,
    private val model: UserDataModel
):UserDataContract.IUserDataPresenter{

    //向model請求獲取User個人資料
    override fun getUser(userName:String) {
        model.getUser(object:IUserData.IUserResult{
            override fun onUserResult(result: UserDataFormat) {
                view.changeUserUI(result)
            }
        },userName)
    }

    //向model請求獲取Users清單
    override fun getUsersList() {
        model.getUsersList(object:IUserData.IUsersListResult{
            override fun onUsersListResult(result: List<UserDataFormat>) {
                view.changeUsersListUI(result)
            }
        })
    }
}