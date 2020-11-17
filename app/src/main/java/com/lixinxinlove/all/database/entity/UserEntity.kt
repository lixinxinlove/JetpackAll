package com.lixinxinlove.all.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "lee_user")

data class  UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var userName: String,
    var userPhone: String,
    var userAddress: String
)