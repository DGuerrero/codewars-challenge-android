package com.quoders.app.codewarschallenge.data.network.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.quoders.app.codewarschallenge.data.network.CodewarsApi
import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.ChallengesAuthored
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted
import com.quoders.app.codewarschallenge.data.network.model.users.UserResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CodewarsApiClient {

    private val codewarsApi: CodewarsApi

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://www.codewars.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        codewarsApi = retrofit.create(CodewarsApi::class.java)
    }

    fun getUser(userName: String) : Observable<UserResponse> {
        return codewarsApi.getUser(userName)
    }

    fun getChallengesCompleted(userName: String) : Call<ChallengesCompleted> {
        return codewarsApi.getChallengesCompleted(userName)
    }

    fun getChallengesAuthored(userName: String) : Call<ChallengesAuthored> {
        return codewarsApi.getChallengesAuthored(userName)
    }
}