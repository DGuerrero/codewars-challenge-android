package com.quoders.app.codewarschallenge

import android.app.Application
import android.arch.persistence.room.Room
import com.quoders.app.codewarschallenge.data.local.database.CodewarsDatabase


class CodewarsApplication : Application() {

    lateinit var database: CodewarsDatabase

    override fun onCreate() {
        super.onCreate()

        initDatabase()
    }

    private fun initDatabase() {
        database = Room.databaseBuilder(applicationContext,
                CodewarsDatabase::class.java, "codewars_database").build()
    }
}