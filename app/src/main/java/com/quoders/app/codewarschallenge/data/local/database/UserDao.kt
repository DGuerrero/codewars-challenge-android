package com.quoders.app.codewarschallenge.data.local.database

import android.arch.persistence.room.*
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

    @Query("SELECT * FROM users WHERE name = :arg0")
    fun getByName(userName: String): Flowable<UserEntity>

    @Delete
    fun delete(user: UserEntity)
}