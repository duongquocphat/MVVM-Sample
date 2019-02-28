package com.shakutara.demomvvm.data.respository

import com.shakutara.demomvvm.entity.RepoSearchResponse
import com.shakutara.demomvvm.service.GithubService
import io.reactivex.Observable

class DataRepository(private val githubService: GithubService) {

    fun searchRepositories(query: String): Observable<RepoSearchResponse> = githubService.searchRepos(query)
}