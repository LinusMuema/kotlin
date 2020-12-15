package com.moose.androidkt.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.Single

@Entity
data class User(val name: String, @PrimaryKey val number: Int, val image:String)

object Data {

    private val names = arrayListOf("John Doe", "Linus Moose", "Peter Kay", "Dankat Dennis", "Mick Jagger", "Jane Doe", "Lorem Ipsum", "Jenny clyde", "Missy clark", "Kate Hudson")

    fun getRxUser(number: Int): Single<User> =
        Single.just(User(names.random(), number, "https://randomuser.me/api/portraits/lego/${(0..5).random()}.jpg"))

    fun getCoroutineUser(number: Int) =
        User(names.random(), number, "https://randomuser.me/api/portraits/lego/${(0..5).random()}.jpg")


}