package com.shakutara.demomvvm.views.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.shakutara.demomvvm.R
import com.shakutara.demomvvm.service.GithubService
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    companion object {
        var githubService: GithubService =
            Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//                .client(okhttpClient)
                .build()
                .create(GithubService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        observeSearchResults()

        //  init a query
        mainViewModel.searchRepos("android")
    }

    private fun observeSearchResults() {
        mainViewModel.repoList.observe(this, Observer {
            Log.e("====", "$it")
        })
    }
}
