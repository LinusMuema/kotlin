package com.moose.androidkt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.moose.androidkt.data.User
import com.moose.androidkt.db.AppDatabase
import com.moose.androidkt.work.Work
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivityViewmodel(application: Application): AndroidViewModel(application) {

    val users: MutableLiveData<List<User>> = MutableLiveData()
    val exception: MutableLiveData<String> = MutableLiveData()

    private val composite = CompositeDisposable()
    private val dao = AppDatabase.getDatabase(application).dao()

    // WorkManager instance
    private val manager = WorkManager.getInstance(application)

    // Our work constraints
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true)
        .build()

    // Define OneTime work
    private val oneTimeWorker = OneTimeWorkRequest.Builder(Work::class.java)
        .setConstraints(constraints)
        .build()

    // Define Periodic work
    private val periodicWork = PeriodicWorkRequest.Builder(Work::class.java, 15, TimeUnit.MINUTES)
        .setConstraints(constraints)
        .build()

    fun getUsers(){
        composite.add(
            dao.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(
                    {users.value = it},
                    {exception.value = it.message})
        )
    }

    fun clearDb(){
        composite.add(dao.deleteUsers().subscribeOn(Schedulers.io()).subscribe())
    }

    fun startWork(){
        manager.enqueue(listOf(oneTimeWorker, periodicWork))
    }

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }

}