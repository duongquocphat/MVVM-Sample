package com.shakutara.demomvvm.service

import com.shakutara.demomvvm.entity.RepoSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepos(
        @Query("q") query: String
    ): Observable<RepoSearchResponse>
}