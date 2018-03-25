package com.quoders.app.codewarschallenge.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log

import com.quoders.app.codewarschallenge.data.network.api.CodewarsApiClient
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by david on 25/03/2018.
 */

class RepositoryJava {

    private val codewarsApiClient: CodewarsApiClient? = null

    fun getUser(userName: String): LiveData<ChallengesCompleted> {
        val data = MutableLiveData<ChallengesCompleted>()
        codewarsApiClient!!.getChallenges(userName).enqueue(object : Callback<ChallengesCompleted> {
            override fun onResponse(call: Call<ChallengesCompleted>, response: Response<ChallengesCompleted>) {
                // error case is left out for brevity
                data.value = response.body()
            }

            override fun onFailure(call: Call<ChallengesCompleted>, t: Throwable) {
                Log.i("", "")
            }
        })

        return data
    }

}
