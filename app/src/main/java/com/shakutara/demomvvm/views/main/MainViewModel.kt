package com.shakutara.demomvvm.views.main

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.shakutara.demomvvm.common.BaseViewModel
import com.shakutara.demomvvm.data.respository.DataRepository
import com.shakutara.demomvvm.entity.RepoSearchResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MainViewModel : BaseViewModel() {
    private val dataRepository = DataRepository(MainActivity.githubService)
    var repoList: MutableLiveData<Int> = MutableLiveData()

    fun searchRepos(query: String) {

        dataRepository.searchRepositories(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<RepoSearchResponse> {
                override fun onComplete() {
                    Log.e("====", "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)

                    Log.e("====", "onSubscribe")
                }

                override fun onNext(t: RepoSearchResponse) {
                    repoList.value = t.totalCount

                    Log.e("====", "onNext")
                }

                override fun onError(e: Throwable) {
                    Log.e("====", "onError")
                }
            })
    }
}