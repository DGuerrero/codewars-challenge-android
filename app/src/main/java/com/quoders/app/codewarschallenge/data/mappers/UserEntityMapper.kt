package com.quoders.app.codewarschallenge.data.mappers

import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.data.network.model.users.UserResponse

fun UserResponseToEntity(userResponse: UserResponse?) : UserEntity {
    val userEntity = UserEntity()
    userEntity.clan = userResponse?.clan
    userEntity.honor = userResponse!!.honor
    userEntity.name = userResponse.username
    userEntity.rank = userResponse.ranks.overall.rank
    userEntity.leaderboardPosition = userResponse.leaderboardPosition
    return userEntity
}