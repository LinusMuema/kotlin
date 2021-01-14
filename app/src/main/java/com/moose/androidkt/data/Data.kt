package com.moose.androidkt.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.quanti.quase.loremkotlinum.Lorem

@Entity
data class User(val name: String, @PrimaryKey val number: Int, val image:String)

object Data {

    private fun getName() = Lorem.name()
    private fun getNumber() = (9999..99999).random()
    private fun getImage() = "https://randomuser.me/api/portraits/lego/${(0..5).random()}.jpg"

    fun getUser(): User = User(getName(), getNumber(), getImage())

}