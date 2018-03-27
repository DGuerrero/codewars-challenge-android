package com.quoders.app.codewarschallenge.ui.challenges

import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum

interface ChallengeCompletedClickListener {
    fun onChallengeItemlClick(challenge: Datum)
}
