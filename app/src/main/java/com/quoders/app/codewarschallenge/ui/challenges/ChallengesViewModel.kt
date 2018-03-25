package com.quoders.app.codewarschallenge.ui.challenges

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.ChallengesAuthored

import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import com.quoders.app.codewarschallenge.data.repository.UserRepository

class ChallengesViewModel : ViewModel() {

    lateinit var challengesCompleted: LiveData<ChallengesCompleted>
    lateinit var challengesAuthored: LiveData<ChallengesAuthored>
    lateinit var userName: String
    private lateinit var usersRepository: UserRepository

    fun init(name: String, repository: UserRepository) {
        userName = name
        usersRepository = repository
        challengesCompleted = usersRepository.getChallengesCompleted(userName)
        challengesAuthored = usersRepository.getChallengesAuthored(userName)
    }
}
