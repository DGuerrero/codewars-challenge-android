package com.quoders.app.codewarschallenge.ui.challenges

import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.DatumAuthored

interface ChallengeAuthoredClickListener {
    fun onChallengeItemlClick(user: DatumAuthored)
}