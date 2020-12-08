package com.moose.androidkt.data

data class User(val id: Int, val name: String, val number: Int, val image:String)

object Data {
    val user1 = User(1, "John Doe", (9999..99999).random(), "https://randomuser.me/api/portraits/lego/1.jpg")
    val user2 = User(2, "Linus Moose", (9999..99999).random(), "https://randomuser.me/api/portraits/lego/2.jpg")
    val user3 = User(3, "Peter Kay", (9999..99999).random(), "https://randomuser.me/api/portraits/lego/3.jpg")
    val user4 = User(4, "Jenny clyde", (9999..99999).random(), "https://randomuser.me/api/portraits/lego/4.jpg")
    val user5 = User(5, "Kate Hudson", (9999..99999).random(), "https://randomuser.me/api/portraits/lego/5.jpg")

    private val users = listOf(user1, user2, user3, user4, user5)

    fun getUsers(): List<User> = users

    fun getUser(id: Int): User = users.first { it.id == id }
}