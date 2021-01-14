package com.moose.androidkt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moose.androidkt.data.Data
import com.moose.androidkt.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun dao(): AppDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                .addCallback(AppBootstrap(context))
                .build()
                .also { INSTANCE = it }
    }


    class AppBootstrap(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = getDatabase(context).dao()
            for (i in 0..49) GlobalScope.launch {
                val user = withContext(Dispatchers.Default) { Data.getUser() }
                dao.addUser(user)
            }
        }
    }
}
