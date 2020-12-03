package com.moose.androidkt.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.moose.androidkt.data.Data
import com.moose.androidkt.db.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Work(context: Context, params: WorkerParameters): Worker(context, params) {

    private val dao = AppDatabase.getDatabase(context).dao()
    private val composite = CompositeDisposable()

    override fun doWork(): Result {
        return try {
            composite.add(dao.addUsers(Data.getUser()).subscribeOn(Schedulers.io()).subscribe())
            Result.success()
        }
        catch (e: Exception){
            Result.failure()
        }
    }

    override fun onStopped() {
        super.onStopped()
        composite.dispose()
    }
}