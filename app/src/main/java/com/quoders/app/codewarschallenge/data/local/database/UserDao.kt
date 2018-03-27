package com.quoders.app.codewarschallenge.data.local.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT * from users")
    fun getAllFlowable(): Flowable<List<UserEntity>>

    @Query("SELECT * from users")
    fun getAllLive(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: UserEntity)

    @Query("SELECT * FROM users WHERE name = :userName")
    fun getByName(userName: String): UserEntity

    @Delete
    fun delete(user: UserEntity)

    @Query("DELETE FROM users")
    fun deleteAll()
}