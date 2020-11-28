package com.moose.androidkt.data

data class User(val name: String, val number: Int, val image:String)

object Data {

    private val names = arrayListOf("John Doe", "Linus Moose", "Peter Kay", "Dankat Dennis", "Mick Jagger", "Jane Doe", "Lorem Ipsum", "Jenny clyde", "Missy clark", "Kate Hudson")
    private val users: ArrayList<User> = ArrayList()

    fun getName() = names.random()
    fun getNumber() = (9999..99999).random()
    fun getImage() = "https://randomuser.me/api/portraits/lego/${(0..5).random()}.jpg"

    fun get(): ArrayList<User> {
        for(i in 0..3) users.add(User(getName(), getNumber(), getImage()))
        return users
    }

    fun getOne(): User = User(getName(), getNumber(), getImage())
}