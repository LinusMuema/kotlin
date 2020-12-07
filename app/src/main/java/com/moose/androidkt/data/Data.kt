package com.moose.androidkt.data

data class User(val name: String, val number: Int, val image:String)

object Data {
    private val names = arrayListOf("John Doe", "Linus Moose", "Peter Kay", "Dankat Dennis", "Mick Jagger", "Jane Doe", "Lorem Ipsum", "Jenny clyde", "Missy clark", "Kate Hudson")
    private val users: ArrayList<User> = ArrayList()

    fun get(): ArrayList<User> {
        for(i in 0..5)
            users.add(User(names.random(), (9999..99999).random(), "https://randomuser.me/api/portraits/lego/$i.jpg"))
        return users
    }
}