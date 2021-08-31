package com.jagertech.githubuser.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jagertech.githubuser.R
import com.jagertech.githubuser.api.ApiManager
import com.jagertech.githubuser.api.DataFormat.UserDataFormat
import com.jagertech.githubuser.model.UserDataModel
import com.jagertech.githubuser.presenter.UserDataContract
import com.jagertech.githubuser.presenter.UserDataPresenter

class UserInfoFragment:Fragment(),UserDataContract.IView {
    lateinit var userAvatar: ImageView
    lateinit var userName: AppCompatTextView
    lateinit var userEmail: AppCompatTextView
    lateinit var userBio: AppCompatTextView
    lateinit var userBlog: AppCompatTextView
    lateinit var userCompany: AppCompatTextView
    lateinit var userFollowers: AppCompatTextView
    lateinit var userFollowing: AppCompatTextView
    lateinit var userPublicRepos: AppCompatTextView
    lateinit var userPublicGists: AppCompatTextView
    lateinit var userCreated: AppCompatTextView
    lateinit var userUpdated: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.user_info_fragment, container, false)

        userName = root.findViewById(R.id.user_name)
        userEmail = root.findViewById(R.id.user_email)
        userBio = root.findViewById(R.id.user_bio)
        userBlog = root.findViewById(R.id.user_blog)
        userCompany = root.findViewById(R.id.user_company)
        userFollowers = root.findViewById(R.id.user_followers)
        userFollowing = root.findViewById(R.id.user_following)
        userPublicRepos = root.findViewById(R.id.user_public_repos)
        userPublicGists = root.findViewById(R.id.user_public_gists)
        userCreated = root.findViewById(R.id.user_created)
        userUpdated = root.findViewById(R.id.user_updated)
        userAvatar = root.findViewById(R.id.user_avatar)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userDataModel = UserDataModel(ApiManager().githubApi)
        val userDataPresenter = UserDataPresenter(this, userDataModel)
        arguments?.getString("username")?.let { Log.d("ASASAS", it) }
        arguments?.getString("username")?.let { userDataPresenter.getUser(it) }
    }

    override fun changeUserUI(result: UserDataFormat) {
        if(TextUtils.isEmpty(result.login)) ((userName.parent) as LinearLayout).visibility = View.GONE else userName.text = result.login
        if(TextUtils.isEmpty(result.email)) ((userEmail.parent) as LinearLayout).visibility = View.GONE else userEmail.text = result.email
        if(TextUtils.isEmpty(result.bio)) ((userBio.parent) as LinearLayout).visibility = View.GONE else userBio.text = result.bio
        if(TextUtils.isEmpty(result.blog)) ((userBlog.parent) as LinearLayout).visibility = View.GONE else userBlog.text = result.blog
        if(TextUtils.isEmpty(result.company)) ((userCompany.parent) as LinearLayout).visibility = View.GONE else userCompany.text = result.company
        if(TextUtils.isEmpty(result.followers.toString())) ((userFollowers.parent) as LinearLayout).visibility = View.GONE else userFollowers.text = result.followers.toString()
        if(TextUtils.isEmpty(result.following.toString())) ((userFollowing.parent) as LinearLayout).visibility = View.GONE else userFollowing.text = result.following.toString()
        if(TextUtils.isEmpty(result.publicRepos.toString())) ((userPublicRepos.parent) as LinearLayout).visibility = View.GONE else userPublicRepos.text = result.publicRepos.toString()
        if(TextUtils.isEmpty(result.publicGists.toString())) ((userPublicGists.parent) as LinearLayout).visibility = View.GONE else userPublicGists.text = result.publicGists.toString()
        if(TextUtils.isEmpty(result.createdAt.toString())) ((userCreated.parent) as LinearLayout).visibility = View.GONE else userCreated.text = result.createdAt.toString()
        if(TextUtils.isEmpty(result.updatedAt.toString())) ((userUpdated.parent) as LinearLayout).visibility = View.GONE else userUpdated.text = result.updatedAt.toString()
        if(TextUtils.isEmpty(result.avatarUrl)) userAvatar.visibility = View.GONE else Glide.with(this)
            .load(result.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(userAvatar)
    }

    override fun changeUsersListUI(result: List<UserDataFormat>) {

    }
}