package com.lixinxinlove.all.http

import com.lixinxinlove.all.http.data.RespData
import com.lixinxinlove.all.http.data.UserRepos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): MutableList<UserRepos>


    @GET("users/{user}/repos")
    fun listRepos1(@Path("user") user: String): Call<RespData<MutableList<UserRepos>>>

}