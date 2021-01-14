package com.moose.androidkt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.toLiveData
import com.moose.androidkt.db.AppDatabase

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).dao()
    val users = dao.getUsers().toLiveData(pageSize = 10)
}