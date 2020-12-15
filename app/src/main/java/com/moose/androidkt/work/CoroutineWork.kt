package com.moose.androidkt.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.moose.androidkt.data.Data
import com.moose.androidkt.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CoroutineWork(context: Context, params: WorkerParameters):CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val dao = AppDatabase.getDatabase(applicationContext).dao()
        return withContext(Dispatchers.IO){
            try {
                val user = Data.getCoroutineUser(inputData.getInt("USER_ID", 0))
                dao.addCoroutineUser(user)
                Result.success()
            }catch (e: Exception){
                Result.failure()
            }
        }
    }
}