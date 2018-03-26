package com.quoders.app.codewarschallenge.ui.search

import android.arch.lifecycle.*
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.data.repository.UserRepository
import com.quoders.app.codewarschallenge.domain.GetUserInteractor
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter(val view: SearchContract.View, private val usersRepository: UserRepository)
    : SearchContract.Presenter, LifecycleObserver {

    private val disposables = CompositeDisposable()
    private val searchUserInteractor = GetUserInteractor(usersRepository)

    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onAttach() {
        view.observeUsersSearch(usersRepository.getUsersSearch())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onDetach() {
        disposables.clear()
    }

    override fun onSearchForUser(userName: String) {
        searchUserInteractor.getUserByName(userName)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<UserEntity> {
                    override fun onSubscribe(d: Disposable) {
                        view.showProgressBar()
                    }

                    override fun onNext(userResponse: UserEntity) {
                    }

                    override fun onError(e: Throwable) {
                        view.dismissProgressBar()
                        view.showUserNotFound()
                    }

                    override fun onComplete() {
                        view.dismissProgressBar()
                    }
                })
    }

    override fun onUsersSearchUpdate(usersEntity: List<UserEntity>) {
        view.updateUsersSearchList(usersEntity)
    }

    override fun onUserClicked(userEntity: UserEntity) {
        view.launchChallengesView(userEntity)
    }

    override fun onOrderByRankClick() {
        usersRepository.getAllUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<UserEntity>> {
                    override fun onSubscribe(d: Disposable) {
                        view.showProgressBar()
                    }

                    override fun onNext(userResponse: List<UserEntity>) {
                        view.updateUsersSearchList(userResponse.sortedByDescending { userEntity -> userEntity.rank })
                        onComplete()
                    }

                    override fun onError(e: Throwable) {
                        view.dismissProgressBar()
                        view.showGenericError()
                    }

                    override fun onComplete() {
                        view.dismissProgressBar()
                    }
                })
    }
}