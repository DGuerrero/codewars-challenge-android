package com.quoders.app.codewarschallenge.data.local.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.quoders.app.codewarschallenge.data.local.entities.ChallengeCompletedEntity

@Dao
interface ChallengesCompletedDao {

    @Query("SELECT * from challenges_completed")
    fun getAllLive(): LiveData<List<ChallengeCompletedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(challenges: List<ChallengeCompletedEntity>)

    @Query("SELECT * FROM challenges_completed WHERE userName = :userName")
    fun getChallengesByUserName(userName: String): List<ChallengeCompletedEntity>

    @Query("SELECT * FROM challenges_completed WHERE userName = :userName")
    fun getChallengesByUserNameLive(userName: String): LiveData<List<ChallengeCompletedEntity>>

    @Delete
    fun delete(challenge: ChallengeCompletedEntity)
}