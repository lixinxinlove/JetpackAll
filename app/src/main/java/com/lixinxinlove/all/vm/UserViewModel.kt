package com.lixinxinlove.all.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lixinxinlove.all.database.UserDatabase
import com.lixinxinlove.all.database.UserRepository
import com.lixinxinlove.all.database.entity.UserEntity
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    private val allUser: LiveData<List<UserEntity>>

    init {
        val userDao = UserDatabase.getDatabase(application, viewModelScope).userDao()
        repository = UserRepository(userDao)
        allUser = repository.allUser
    }

    fun insert(user: UserEntity) = viewModelScope.launch {
        repository.insert(user)
    }

    fun allUserList() : LiveData<List<UserEntity>> {
        return allUser
    }


}