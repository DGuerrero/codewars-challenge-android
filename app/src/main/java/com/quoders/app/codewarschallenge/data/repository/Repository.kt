package com.quoders.app.codewarschallenge.data.repository

import android.arch.lifecycle.LiveData
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import io.reactivex.Observable

interface Repository {
    fun getUser(userName: String) : Observable<UserEntity>

    //fun getChallengesCompleted(userName: String) : LiveData<List<ChallengesCompleted>>
}
