package com.moose.androidkt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moose.androidkt.R
import com.moose.androidkt.adapters.ListAdapter
import com.moose.androidkt.data.Data
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview.apply {
            setHasFixedSize(true)
            adapter = ListAdapter(Data.get())
        }
    }
}