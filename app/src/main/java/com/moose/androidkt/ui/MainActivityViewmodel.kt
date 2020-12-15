package com.moose.androidkt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.moose.androidkt.data.User
import com.moose.androidkt.db.AppDatabase
import com.moose.androidkt.work.CoroutineWork
import com.moose.androidkt.work.RxWork
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

    // Create our data
    private fun getData() = Data.Builder().putInt("USER_ID", (9999..99999).random()).build()

    // Work using RxJava
    private val rxWork = PeriodicWorkRequestBuilder<RxWork>(15, TimeUnit.MINUTES)
        .setInputData(getData())
        .build()

    // Work using coroutines
    private val coroutinesWork = PeriodicWorkRequestBuilder<CoroutineWork>(15, TimeUnit.MINUTES)
        .setInputData(getData())
        .build()

    fun startWork(){
        manager.enqueue(listOf(rxWork, coroutinesWork))
    }


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

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }

}