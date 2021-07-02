package com.lixinxinlove.all.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lixinxinlove.all.http.data.UserRepos
import com.lixinxinlove.all.repository.UserReposRepository
import kotlinx.coroutines.launch

/**
 *   author ： lixinxin
 *   time    ： 2021/7/1
 *   email：895330766@qq.com
 */
class RetrofitViewModel : ViewModel() {

     val userReposLiveData = MutableLiveData<MutableList<UserRepos>>()

    private val userReposRepository: UserReposRepository by lazy { UserReposRepository() }


    fun userRepos(name:String){
        viewModelScope.launch {
            userReposLiveData.value=userReposRepository.getUserRepos(name)
        }
    }

}