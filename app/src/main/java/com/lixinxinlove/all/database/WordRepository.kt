package com.lixinxinlove.all.database

import androidx.annotation.WorkerThread
import com.lixinxinlove.all.database.dao.WordDao
import com.lixinxinlove.all.database.entity.Word
import kotlinx.coroutines.flow.Flow

/**
 *   author ： lixinxin
 *   time    ： 2021/6/30
 *   email：895330766@qq.com
 */
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getFlowWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}