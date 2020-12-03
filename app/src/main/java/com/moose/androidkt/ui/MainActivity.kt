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
    private val users:ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this).get(MainActivityViewmodel::class.java)
        viewmodel.startWork()
        viewmodel.getUsers()

        recyclerview.apply {
            setHasFixedSize(true)
            adapter = ListAdapter(users)
        }

        btn_clear.setOnClickListener {
            viewmodel.clearDb()
        }

        viewmodel.users.observe(this, {
            users.clear()
            users.addAll(it)
            recyclerview.adapter!!.notifyDataSetChanged()
        })

        viewmodel.exception.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}