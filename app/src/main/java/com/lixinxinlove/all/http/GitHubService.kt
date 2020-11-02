package com.lixinxinlove.all.http

import com.lixinxinlove.all.http.data.UserRepos
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): MutableList<UserRepos>

}