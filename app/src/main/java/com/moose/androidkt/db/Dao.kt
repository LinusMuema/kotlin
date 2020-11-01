package com.moose.androidkt.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moose.androidkt.network.User
import com.moose.androidkt.network.Users
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface Dao {
    @Query("SELECT * FROM user WHERE id=:id")
    fun getOneUser(id: Int): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(users: Users): Completable
}