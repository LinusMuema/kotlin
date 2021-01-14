package com.moose.androidkt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.moose.androidkt.data.User
import com.moose.androidkt.databinding.ListItemBinding

class UsersAdapter: PagedListAdapter<User, UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User) =
        oldItem.number == newItem.number

    override fun areContentsTheSame(oldItem: User, newItem: User) =
        oldItem == newItem

}

class UserViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.name.text = user.name
        binding.phone.text = user.number.toString()
        binding.profile.load(user.image)
    }
}