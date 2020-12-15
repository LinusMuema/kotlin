package com.moose.androidkt.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moose.androidkt.data.User
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface Dao {
    @Query("SELECT * FROM user")
    fun getUsers(): Flowable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRxUser(user: User): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCoroutineUser(users: User)

    @Query("DELETE FROM user")
    fun deleteUsers(): Completable
}