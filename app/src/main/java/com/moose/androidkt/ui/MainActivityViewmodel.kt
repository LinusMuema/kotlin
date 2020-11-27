package com.moose.androidkt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.moose.androidkt.data.Data
import com.moose.androidkt.data.User
import com.moose.androidkt.work.Worker

class MainActivityViewmodel(application: Application): AndroidViewModel(application) {

    val users: MutableLiveData<ArrayList<User>> = MutableLiveData()

    // WorkManager instance
    private val manager = WorkManager.getInstance(application)

    // Our work constraints
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true)
        .build()

    // Define the work
    private val work = OneTimeWorkRequestBuilder<Worker>().setConstraints(constraints).build()

    // The work info
    val workInfo = manager.getWorkInfoByIdLiveData(work.id)

    fun getUsers(){
        users.value = Data.get()
    }

    fun getUpdate(){
        manager.enqueue(work)
    }
}