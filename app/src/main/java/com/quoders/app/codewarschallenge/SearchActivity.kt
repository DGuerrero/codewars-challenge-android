package com.quoders.app.codewarschallenge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.quoders.app.codewarschallenge.data.network.api.CodewarsApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val codewarsApi = CodewarsApiClient()
        codewarsApi.getUser("g964")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
            it.codeChallenges
        })

    }
}
