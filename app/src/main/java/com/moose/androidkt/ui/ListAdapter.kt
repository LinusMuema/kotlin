package com.moose.androidkt.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moose.androidkt.R
import com.moose.androidkt.data.User
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val data: List<User>): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            Glide.with(itemView.context).load(user.image).into(itemView.profile)
            itemView.name.text = user.name
            itemView.phone.text = user.number.toString()
        }

    }
}
