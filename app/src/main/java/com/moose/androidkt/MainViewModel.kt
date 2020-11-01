package com.moose.androidkt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.moose.androidkt.db.AppDatabase
import com.moose.androidkt.network.Service
import com.moose.androidkt.network.Users
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application): AndroidViewModel(application) {

    val data: MutableLiveData<String> = MutableLiveData()
    val exception: MutableLiveData<String> = MutableLiveData()
    private val composite = CompositeDisposable()
    private val dao = AppDatabase.getDatabase(application).dao()

    fun getNetworkData() {
        composite.add(
            Service.retrofit().getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        data.value = it.toString()
                        addUsers(it)
                    },
                    {exception.value = it.message})
        )
    }

    private fun addUsers(users: Users) {
        composite.add(
            dao.addUsers(users)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(
                    {exception.value = "Updated database"},
                    {exception.value = it.message})
        )
    }

    fun getRoomData() {
        composite.add(
            dao.getOneUser((1..10).random())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {data.value = it.toString()},
                    {exception.value = it.message})
        )
    }

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }
}