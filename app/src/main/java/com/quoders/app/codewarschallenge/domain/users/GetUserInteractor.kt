package com.quoders.app.codewarschallenge.domain.users

import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.data.repository.UserRepository
import io.reactivex.Observable


class GetUserInteractor(val respository: UserRepository) {

    fun getUserByName(name: String) : Observable<UserEntity> {
        return respository.getUser(name)
    }
}