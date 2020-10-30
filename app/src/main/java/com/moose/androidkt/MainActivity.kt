package com.moose.androidkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewmodel.data.observe(this, {
            progress.visibility = View.GONE
            data.text = it
        })

        viewmodel.exception.observe(this, {
            progress.visibility = View.GONE
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        btn_network.setOnClickListener {
            progress.visibility = View.VISIBLE
            viewmodel.getNetworkData()
        }

        btn_room.setOnClickListener {
            progress.visibility = View.VISIBLE
            viewmodel.getRoomData()
        }
    }
}