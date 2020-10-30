package com.moose.androidkt.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moose.androidkt.network.Users

class Converters {

    @TypeConverter
    fun usersToJson(users: List<Users>): String = Gson().toJson(users)

    @TypeConverter
    fun jsonToUsers(json: String): List<Users> = Gson().fromJson(json, Array<Users>::class.java).toList()
}