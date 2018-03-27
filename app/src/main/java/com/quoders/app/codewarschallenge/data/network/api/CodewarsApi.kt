package com.quoders.app.codewarschallenge.data.network

import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.ChallengesAuthored
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import com.quoders.app.codewarschallenge.data.network.model.users.UserResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CodewarsApi {

    @GET("api/v1/users/{username}")
    fun getUser(@Path("username") userName: String): Observable<UserResponse>

    @GET("api/v1/users/{username}/code-challenges/completed")
    fun getChallengesCompleted(@Path("username") userName: String,
                               @Query("page_number") pageNumber: Int): Call<ChallengesCompleted>

    @GET("api/v1/users/{username}/code-challenges/completed")
    fun getChallengesCompletedObservable(@Path("username") userName: String,
                                         @Query("page_number") pageNumber: Int): Observable<ChallengesCompleted>

    @GET("api/v1/users/{username}/code-challenges/authored")
    fun getChallengesAuthored(@Path("username") userName: String): Call<ChallengesAuthored>
}