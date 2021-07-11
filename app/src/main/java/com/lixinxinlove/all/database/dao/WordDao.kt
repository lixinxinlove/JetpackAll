package com.lixinxinlove.all.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lixinxinlove.all.database.entity.Word
import kotlinx.coroutines.flow.Flow

/**
 *   author ： lixinxin
 *   time    ： 2021/6/30
 *   email：895330766@qq.com
 */
@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): List<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getFlowWords(): Flow<List<Word>>
}