package com.jagertech.githubuser.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jagertech.githubuser.R
import com.jagertech.githubuser.api.ApiManager
import com.jagertech.githubuser.api.DataFormat.UserDataFormat
import com.jagertech.githubuser.api.GithubApi
import com.jagertech.githubuser.model.UserDataModel
import com.jagertech.githubuser.presenter.UserDataContract
import com.jagertech.githubuser.presenter.UserDataPresenter
import com.jagertech.githubuser.view.adapter.Item
import com.jagertech.githubuser.view.adapter.UsersListAdapter
import retrofit2.Response

class UsersListFragment: Fragment(),UserDataContract.IView,UsersListAdapter.UsersListAdapterInteraction{

    private lateinit var itemViewer: RecyclerView
    val myItemList = arrayListOf<UserDataFormat>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root:View = inflater.inflate(R.layout.users_list_fragment, container, false)

        itemViewer = root.findViewById(R.id.itemViewer)
        initAdapter()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userDataModel = UserDataModel(ApiManager().githubApi)
        val userDataPresenter = UserDataPresenter(this, userDataModel)
        userDataPresenter.getUsersList()
    }

    private fun initAdapter(){
        val usersListAdapter = UsersListAdapter(this)
        usersListAdapter.dataList = myItemList
        itemViewer.layoutManager = LinearLayoutManager(context)
        itemViewer.adapter = usersListAdapter
    }

    override fun changeUsersListUI(result: List<UserDataFormat>) {
        for(data in result){
            data.let { myItemList.add(it) }
        }
        itemViewer.adapter?.notifyDataSetChanged()
    }

    override fun changeUserUI(result: UserDataFormat) {

    }

    override fun onUserItemClick(userName:String) {
        val bundle = bundleOf("username" to userName)
        findNavController().navigate(R.id.action_usersListFragment_to_userInfoFragment,bundle)
    }
}
