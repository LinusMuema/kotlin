package com.moose.androidkt.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.moose.androidkt.R
import com.moose.androidkt.data.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private lateinit var viewmodel: MainActivityViewmodel
    private val users: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this).get(MainActivityViewmodel::class.java)
        viewmodel.getUsers()

        recyclerview.apply {
            setHasFixedSize(true)
            adapter = ListAdapter(users)
        }

        btn_add.setOnClickListener {
            viewmodel.getUpdate()
        }

        viewmodel.users.observe(this, {
            users.addAll(it)
        })

        viewmodel.workInfo.observe(this, {
            if (it.state.isFinished) {
                val workError = it.outputData.getString("WORK_ERROR")
                if (workError != null) {
                    Toast.makeText(this, workError, Toast.LENGTH_SHORT).show()
                } else {
                    val userName = it.outputData.getString("USER_NAME")!!
                    val userNumber = it.outputData.getInt("USER_NUMBER", 0)
                    val userImage = it.outputData.getString("USER_IMAGE")!!
                    Log.d("users", "onCreate: $userName")
                    users.add(User(userName, userNumber, userImage))
                    recyclerview.adapter!!.notifyDataSetChanged()
                }
            }
        })
    }
}