package com.moose.androidkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.paging.toLiveData
import com.moose.androidkt.databinding.ActivityMainBinding
import com.moose.androidkt.db.AppDatabase

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val usersAdapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.users.observe(this, { usersAdapter.submitList(it) })
        binding.recycler.adapter = usersAdapter
        setContentView(binding.root)
    }
}