package com.quoders.app.codewarschallenge.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity


@Database(entities = [(UserEntity::class)], version = 1, exportSchema = false)
abstract class CodewarsDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}