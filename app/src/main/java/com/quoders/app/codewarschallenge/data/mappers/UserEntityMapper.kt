package com.quoders.app.codewarschallenge.data.mappers

import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.data.network.model.users.UserResponse

fun UserResponseToEntity(userResponse: UserResponse?) : UserEntity {
    val userEntity = UserEntity()
    userEntity.clan = userResponse?.clan
    userEntity.honor = userResponse!!.honor
    userEntity.name = userResponse.username
    return userEntity
}