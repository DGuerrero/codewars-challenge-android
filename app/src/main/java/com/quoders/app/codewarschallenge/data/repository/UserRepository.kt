package com.quoders.app.codewarschallenge.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.VisibleForTesting
import com.quoders.app.codewarschallenge.data.local.database.UserDao
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.data.mappers.UserResponseToEntity
import com.quoders.app.codewarschallenge.data.network.api.CodewarsApiClient
import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.ChallengesAuthored
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * NOTE: THIS IS A VERY SIMPLIFIED VERSION OF THE REPOSITORY PATTERN ONLY FOR CODE CHALLENGE PURPOSE
 *
 * In case we have the user requested in the local database we retrieve it from there,
 * otherwise we make a request to the backend and then store in the database
 */
class UserRepository(val codewarsApiClient: CodewarsApiClient, val userDao: UserDao) : Repository {

    override fun getUser(userName: String): Observable<UserEntity> =
            getUserFromLocalDb(userName).onErrorReturn({ getUserFromApi(userName).blockingFirst() })


    @VisibleForTesting
    fun getUserFromLocalDb(userName: String): Observable<UserEntity> {
        return Observable.fromCallable(
                { userDao.getByName(userName) })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
    }

    @VisibleForTesting
    fun getUserFromApi(userName: String): Observable<UserEntity> {
        return codewarsApiClient.getUser(userName)
                .subscribeOn(Schedulers.single())
                .map({ userResponse -> UserResponseToEntity(userResponse) })
                .doOnNext({ userEntity -> userDao.insertAll(userEntity)
                })
    }

    fun getUsersSearch() : LiveData<List<UserEntity>> = userDao.getAllLive()

    override fun getChallengesCompleted(userName: String): LiveData<ChallengesCompleted> {
        val data = MutableLiveData<ChallengesCompleted>()

        codewarsApiClient.getChallengesCompleted(userName).enqueue(object : Callback<ChallengesCompleted> {
            override fun onResponse(call: Call<ChallengesCompleted>, response: Response<ChallengesCompleted>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ChallengesCompleted>, t: Throwable) {
                data.postValue(null)
            }
        })

        return data
    }

    override fun getChallengesAuthored(userName: String): LiveData<ChallengesAuthored> {
        val data = MutableLiveData<ChallengesAuthored>()

        codewarsApiClient.getChallengesAuthored(userName).enqueue(object : Callback<ChallengesAuthored> {
            override fun onResponse(call: Call<ChallengesAuthored>, response: Response<ChallengesAuthored>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ChallengesAuthored>, t: Throwable) {
                data.postValue(null)
            }
        })

        return data
    }
}