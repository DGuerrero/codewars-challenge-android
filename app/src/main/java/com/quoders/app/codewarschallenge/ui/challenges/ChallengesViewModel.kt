package com.quoders.app.codewarschallenge.ui.challenges

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.ChallengesAuthored
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import com.quoders.app.codewarschallenge.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class ChallengesViewModel : ViewModel() {

    lateinit var challengesCompleted: MutableLiveData<ChallengesCompleted>
    lateinit var challengesAuthored: MutableLiveData<ChallengesAuthored>

    lateinit var userName: String
    private lateinit var usersRepository: UserRepository
    private var currentChallengePage = 0

    fun init(name: String, repository: UserRepository) {
        userName = name
        usersRepository = repository
        challengesCompleted = usersRepository.getChallengesCompleted(userName, currentChallengePage)
        challengesAuthored = usersRepository.getChallengesAuthored(userName)
    }

    fun getNextChallengeCompletedPage() : Boolean {
        if(currentChallengePage < challengesCompleted.value?.totalPages!!) {
            usersRepository.getChallengesCompletedObservable(userName, currentChallengePage++)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ challenge -> challengesCompleted.postValue(challenge) })
            return true
        }
        return false
    }
}
