package com.moose.androidkt.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.moose.androidkt.data.Data as Users

class Worker(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        return try {
            val output = workDataOf(
                "USER_NAME" to Users.getName(),
                "USER_NUMBER" to Users.getNumber(),
                "USER_IMAGE" to Users.getImage())
            Result.success(output)
        }
        catch (e: Exception){
            val exception = workDataOf("WORK_ERROR" to e.localizedMessage)
            Result.failure(exception)
        }
    }
}