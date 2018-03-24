package com.quoders.app.codewarschallenge.data.local.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT * from users")
    fun getAllFlowable(): Flowable<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: UserEntity)
}