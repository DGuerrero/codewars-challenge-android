package com.quoders.app.codewarschallenge.data.local.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
class UserEntity {

    @PrimaryKey
    var name: String = ""

    @ColumnInfo(name = "rank")
    var rank: Long = 0

    @ColumnInfo(name = "clan")
    var clan: String? = null

    @ColumnInfo(name = "honor")
    var honor: Long = 0

    @ColumnInfo(name = "date_added")
    var addedOn: Long = 0

    @ColumnInfo(name = "leaderboardPosition")
    var leaderboardPosition: Long = 0
}