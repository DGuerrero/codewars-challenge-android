package com.quoders.app.codewarschallenge.ui.search

import android.arch.lifecycle.LiveData
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity

interface SearchContract {

    interface View {
        fun showProgressBar()

        fun dismissProgressBar()

        fun showUserNotFound()

        fun updateUsersSearchList(usersEntity: List<UserEntity>)

        fun observeUsersSearch(usersSearch: LiveData<List<UserEntity>>)

        fun launchChallengesView(user: UserEntity)
    }

    interface Presenter {

        fun onSearchForUser(userName: String)

        fun onUsersSearchUpdate(usersEntity: List<UserEntity>)

        fun onUserClicked(userEntity: UserEntity)
    }
}