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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ProfileFragmentArgs by navArgs()
        val user = Data.getUser(args.userId)

        Glide.with(requireContext()).load(user.image).into(image)
        name.text = resources.getString(R.string.name, user.name)
        number.text = resources.getString(R.string.number, user.number.toString())
    }
}