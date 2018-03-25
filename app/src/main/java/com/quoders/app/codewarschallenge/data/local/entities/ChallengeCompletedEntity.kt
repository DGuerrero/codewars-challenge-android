package com.quoders.app.codewarschallenge.data.local.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "challenges_completed")
class ChallengeCompletedEntity {

    @PrimaryKey
    var userName: String = ""

    @ColumnInfo(name = "chaallengesCompleted")
    var chaallengesCompleted: List<ChallengeCompletedEntity>? = null
}