package com.lixinxinlove.all.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *   author ： lixinxin
 *   time    ： 2021/6/30
 *   email：895330766@qq.com
 */
@Entity(tableName = "word_table")
class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)