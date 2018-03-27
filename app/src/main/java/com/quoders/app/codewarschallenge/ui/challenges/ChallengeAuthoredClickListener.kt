package com.quoders.app.codewarschallenge.ui.challenges

import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum

interface ChallengeAuthoredClickListener {
    fun onChallengeItemlClick(challenge: Datum)
}
