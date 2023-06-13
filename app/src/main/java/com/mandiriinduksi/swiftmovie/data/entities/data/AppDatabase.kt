package com.mandiriinduksi.swiftmovie.data.entities.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.RoomDatabase
import com.mandiriinduksi.swiftmovie.data.dao.UserDao
import java.util.concurrent.locks.Lock

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    @Volatile private var instance : AppDatabase? = null

    abstract fun userDao() : UserDao

    private val LOCK = Any()

    operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
        instance ?: buildDatabase(context)
    }

}