package com.quoders.app.codewarschallenge.data.repository

import android.arch.lifecycle.LiveData
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.ChallengesAuthored
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import io.reactivex.Observable

interface Repository {

    fun getUser(userName: String) : Observable<UserEntity>

    fun getAllUsers() : Observable<List<UserEntity>>

    fun getChallengesCompleted(userName: String, pageNumber: Int) : LiveData<ChallengesCompleted>

    fun getChallengesAuthored(userName: String) : LiveData<ChallengesAuthored>
}
