package com.quoders.app.codewarschallenge.data.network

import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import com.quoders.app.codewarschallenge.data.network.model.users.UserResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CodewarsApi {

    @GET("api/v1/users/{username}")
    fun getUser(@Path("username") userName: String): Observable<UserResponse>

    @GET("api/v1/users/{username}/code-challenges/completed?page=0")
    fun getChallengesCompleted(@Path("username") userName: String): Call<ChallengesCompleted>
}