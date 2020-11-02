package com.lixinxinlove.all.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lixinxinlove.all.database.dao.UserDao
import com.lixinxinlove.all.database.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(UserEntity::class), version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    private class UserDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var userDao = database.userDao()
                    // Delete all content here.
                    userDao.deleteAll()
                    // Add sample todos.
                    var todo = UserEntity(0, "小李", "content", "三里同")
                    userDao.insert(todo)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "room_database").addCallback(UserDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}