package com.quoders.app.codewarschallenge.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
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
import java.util.*


/**
 * NOTE: THIS IS A VERY SIMPLIFIED VERSION OF THE REPOSITORY PATTERN ONLY FOR CODE CHALLENGE PURPOSE
 *
 *  Right now we look into the database to find the data, if not found retrieve from backend and save in the DB
 *  But there is no refresh mechanism in place. Ideally we could implement a time based refresh functionality or/and by demand
 */
class UserRepository(val codewarsApiClient: CodewarsApiClient, val userDao: UserDao) : Repository {

    override fun getUser(userName: String): Observable<UserEntity> =
            getUserFromLocalDb(userName).onErrorReturn({ getUserFromApi(userName).blockingFirst() })

    override fun getAllUsers(): Observable<List<UserEntity>> {
        return userDao.getAllFlowable().toObservable()
    }

    fun getUsersSearch() : LiveData<List<UserEntity>> = userDao.getAllLive()

    fun getChallengesCompletedObservable(userName: String, pageNumber: Int): Observable<ChallengesCompleted> {
        return codewarsApiClient.getChallengesCompletedObservable(userName, pageNumber)
                .subscribeOn(Schedulers.single())
    }

    override fun getChallengesCompleted(userName: String, pageNumber: Int): MutableLiveData<ChallengesCompleted> {
        val data = MutableLiveData<ChallengesCompleted>()

        codewarsApiClient.getChallengesCompleted(userName, pageNumber).enqueue(object : Callback<ChallengesCompleted> {
            override fun onResponse(call: Call<ChallengesCompleted>, response: Response<ChallengesCompleted>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ChallengesCompleted>, t: Throwable) {
                data.postValue(null)
            }
        })

        return data
    }

    override fun getChallengesAuthored(userName: String): MutableLiveData<ChallengesAuthored> {
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

    private fun getUserFromLocalDb(userName: String): Observable<UserEntity> {
        return Observable.fromCallable(
                { userDao.getByName(userName) })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getUserFromApi(userName: String): Observable<UserEntity> {
        return codewarsApiClient.getUser(userName)
                .subscribeOn(Schedulers.single())
                .map({ userResponse -> UserResponseToEntity(userResponse) })
                .doOnNext({ userEntity ->
                    userEntity.addedOn = Calendar.getInstance().timeInMillis
                    userDao.insertAll(userEntity)
                })
    }
}