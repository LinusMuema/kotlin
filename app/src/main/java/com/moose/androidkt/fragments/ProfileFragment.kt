package com.moose.androidkt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.moose.androidkt.R
import com.moose.androidkt.data.Data
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.list_item.view.*

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}