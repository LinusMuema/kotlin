package com.moose.androidkt.network

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.Observable
import retrofit2.http.GET

class Users : ArrayList<User>()

@Entity
data class User(@PrimaryKey val id: Int, val email: String, val name: String, val phone: String)

interface Endpoints {

    @GET("/users")
    fun getUsers(): Observable<Users>
}