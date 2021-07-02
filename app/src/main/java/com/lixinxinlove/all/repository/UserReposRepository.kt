package com.lixinxinlove.all.repository

import com.lixinxinlove.all.http.GitHubService
import com.lixinxinlove.all.http.RetrofitFactory
import com.lixinxinlove.all.http.data.UserRepos

/**
 *   author ： lixinxin
 *   time    ： 2021/7/1
 *   email：895330766@qq.com
 */
class UserReposRepository {

    private val service = RetrofitFactory.getRetrofit().create(GitHubService::class.java)

    suspend fun getUserRepos(name: String): MutableList<UserRepos> {
        return service.listRepos(name)
    }

}