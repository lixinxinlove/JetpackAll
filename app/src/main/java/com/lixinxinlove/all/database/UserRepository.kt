package com.lixinxinlove.all.database

import androidx.lifecycle.LiveData
import com.lixinxinlove.all.database.dao.UserDao
import com.lixinxinlove.all.database.entity.UserEntity

class UserRepository(private val userDao: UserDao) {

    val allUser: LiveData<List<UserEntity>> = userDao.getUserList()

    suspend fun insert(user: UserEntity) {
        userDao.insert(user)
    }

    suspend fun delete(user: UserEntity) {
        userDao.deleteUser(user.id)
    }

}