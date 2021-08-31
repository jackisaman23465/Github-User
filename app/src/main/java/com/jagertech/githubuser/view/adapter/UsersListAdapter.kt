package com.jagertech.githubuser.view.adapter

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jagertech.githubuser.R
import com.jagertech.githubuser.api.DataFormat.UserDataFormat

class UsersListAdapter(private val  listener: UsersListAdapterInteraction): RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>() {

    lateinit var context: Context
    var dataList = listOf<UserDataFormat>()

    interface UsersListAdapterInteraction {
        fun onUserItemClick(userName:String)
    }

    inner class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userItem: LinearLayout = itemView.findViewById(R.id.user_item)
        private val userAvatar: ImageView = itemView.findViewById(R.id.user_avatar)
        val userName: AppCompatTextView = itemView.findViewById(R.id.user_name)
        val userHomeUrl: AppCompatTextView = itemView.findViewById(R.id.user_home_url)

        fun bind(item: UserDataFormat){
            userItem.setOnClickListener {
                listener.onUserItemClick(item.login)
            }
            Glide.with(context)
                .load(item.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(userAvatar)
            userName.text = item.login
            userHomeUrl.text = item.htmlUrl
        }
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_github_user, parent, false)
        return UsersListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}