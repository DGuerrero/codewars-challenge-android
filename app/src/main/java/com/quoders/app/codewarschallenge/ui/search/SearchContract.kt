package com.quoders.app.codewarschallenge.ui.search

import android.arch.lifecycle.LiveData
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity

interface SearchContract {

    interface View {
        fun showProgressBar()

        fun dismissProgressBar()

        fun showEmptySearchView()

        fun showUserNotFound()

        fun showLastSearchView()

        fun updateUsersSearchList(usersEntity: List<UserEntity>)

        fun observeUsersSearch(usersSearch: LiveData<List<UserEntity>>)

        fun launchChallengesView(userEntity: UserEntity)
    }

    interface Presenter {

        fun onSearchForUser(userName: String)

        fun onUsersSearchUpdate(usersEntity: List<UserEntity>)

        fun onUserClicked(usersEntity: UserEntity)
    }
}