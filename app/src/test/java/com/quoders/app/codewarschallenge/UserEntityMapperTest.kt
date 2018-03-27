package com.quoders.app.codewarschallenge

import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.data.mappers.UserResponseToEntity
import com.quoders.app.codewarschallenge.data.network.model.users.Overall
import com.quoders.app.codewarschallenge.data.network.model.users.Ranks
import com.quoders.app.codewarschallenge.data.network.model.users.UserResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

class UserEntityMapperTest {

    @Test fun given_validUserResponseData_when_Mapped_then_ReturnWellFormattedEntity() {
        //  given
        val response = UserResponse()
        response.clan = "AClan"
        response.honor = 12345
        response.ranks = Ranks()
        response.ranks.overall = Overall()
        response.ranks.overall.rank = -5
        response.leaderboardPosition = 100
        response.username = "David"

        //  when
        val userEntity: UserEntity = UserResponseToEntity(response)

        //  then
        assertEquals(userEntity.honor, 12345)
        assertEquals(userEntity.clan, "AClan")
        assertEquals(userEntity.rank, -5)
        assertEquals(userEntity.leaderboardPosition, 100)
    }
}