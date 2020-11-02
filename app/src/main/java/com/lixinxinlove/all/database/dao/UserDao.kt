package com.lixinxinlove.all.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lixinxinlove.all.database.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * from lee_user")
    fun getUserList(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity)

    @Query("DELETE FROM lee_user")
    suspend fun deleteAll()

    @Query("DELETE FROM lee_user WHERE id=:id ")
    suspend fun deleteUser(id: Int)

    @Delete
    suspend fun deleteOneUser(user: UserEntity)

}