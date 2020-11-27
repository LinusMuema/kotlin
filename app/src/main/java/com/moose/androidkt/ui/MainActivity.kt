package com.moose.androidkt.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.moose.androidkt.R
import com.moose.androidkt.data.Data

class MainActivity: AppCompatActivity() {

    private lateinit var viewmodel: MainActivityViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this).get(MainActivityViewmodel::class.java)
        viewmodel.getUsers()
        viewmodel.getUpdate()

        viewmodel.users.observe(this, {
            Log.d("Users", "onCreate: $it")
        })

        viewmodel.workInfo.observe(this, {
            if (it.state.isFinished) {
                val output = it.outputData.keyValueMap[Data.WORK_KEY]
                Log.d("Work", "getUpdate: $output")
            }
        })
    }
}