package com.jagertech.githubuser.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jagertech.githubuser.R
import com.jagertech.githubuser.api.ApiManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var apiManager = ApiManager()
    }
}