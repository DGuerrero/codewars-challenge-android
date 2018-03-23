package com.quoders.app.codewarschallenge.data.network

import com.quoders.app.codewarschallenge.data.network.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CodewarsApi {

    @GET("api/v1/users/{username}")
    fun getUser(@Path("username") userName: String): Observable<UserResponse>
}