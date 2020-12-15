package com.moose.androidkt.work

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.moose.androidkt.data.Data
import com.moose.androidkt.db.AppDatabase
import io.reactivex.Single

class RxWork(context: Context, params: WorkerParameters): RxWorker(context, params) {

    override fun createWork(): Single<Result> {
        val dao = AppDatabase.getDatabase(applicationContext).dao()

        return Data.getRxUser(inputData.getInt("USER_ID", 0))
            .flatMap {
                dao.addRxUser(it)
                    .toSingleDefault(Result.success())
                    .onErrorReturn{ Result.failure() }
            }
    }
}