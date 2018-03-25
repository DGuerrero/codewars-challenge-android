package com.quoders.app.codewarschallenge.ui.search

import com.quoders.app.codewarschallenge.data.local.entities.UserEntity

interface UserItemClickListener {
    fun onUserItemlClick(user: UserEntity)
}
