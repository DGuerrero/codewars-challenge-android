package com.quoders.app.codewarschallenge.data.repository

import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import io.reactivex.Observable

interface Repository {
    fun getUser(userName: String) : Observable<UserEntity>
}
