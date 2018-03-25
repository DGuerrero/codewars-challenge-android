package com.quoders.app.codewarschallenge

import android.app.Application
import android.arch.persistence.room.Room
import com.quoders.app.codewarschallenge.data.local.database.CodewarsDatabase
import com.quoders.app.codewarschallenge.data.network.api.CodewarsApiClient
import com.quoders.app.codewarschallenge.data.repository.UserRepository


class CodewarsApplication : Application() {

    /**
     * NOTE: This dependencies should be injected using Dagger 2, for the sake of the code challenge I
     * will just provide them from here
     */
    lateinit var database: CodewarsDatabase
    lateinit var repository: UserRepository

    override fun onCreate() {
        super.onCreate()

        initDatabase()
        initRepository()
    }

    private fun initDatabase() {
        database = Room.databaseBuilder(applicationContext,
                CodewarsDatabase::class.java, "codewars_database").build()
    }

    private fun initRepository() {
        repository = UserRepository(CodewarsApiClient(), database.userDao())
    }
}