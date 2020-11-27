package com.moose.androidkt.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.moose.androidkt.data.Data as Users

class Worker(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        return try {
            val output = workDataOf(Users.WORK_KEY to Users.getOne().toString())
            Result.success(output)
        }
        catch (e: Exception){
            val exception = workDataOf(Users.WORK_KEY to e.localizedMessage)
            Result.failure(exception)
        }
    }
}