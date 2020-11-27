package com.moose.androidkt.data

data class User(val name: String, val number: Int, val image:String)

object Data {

    const val WORK_KEY = "users_key"
    private val names = arrayListOf("John Doe", "Linus Moose", "Peter Kay", "Dankat Dennis", "Mick Jagger", "Jane Doe", "Lorem Ipsum", "Jenny clyde", "Missy clark", "Kate Hudson")
    private val users: ArrayList<User> = ArrayList()

    private fun getName() = names.random()
    private fun getNumber() = (9999..99999).random()
    private fun getImage() = "https://randomuser.me/api/portraits/lego/${(0..5).random()}.jpg"

    fun get(): ArrayList<User> {
        for(i in 0..5) users.add(User(getName(), getNumber(), getImage()))
        return users
    }

    fun getOne(): User = User(getName(), getNumber(), getImage())
}