package com.quoders.app.codewarschallenge.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum

class ChallengeDetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_EXTRA_CHALLENGE_COMPLETED = "ChallengeDetailActivity.INTENT_EXTRA_CHALLENGE_COMPLETED"
        const val INTENT_EXTRA_CHALLENGE_AUTHORED = "ChallengeDetailActivity.INTENT_EXTRA_CHALLENGE_AUTHORED"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)

        val completed: Datum? =
                intent.getSerializableExtra(INTENT_EXTRA_CHALLENGE_COMPLETED)
                        as? com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum

        val authored: com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum? =
                intent.getSerializableExtra(INTENT_EXTRA_CHALLENGE_AUTHORED)
                        as? com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum

        if(completed != null) {
            displayChallengeCompletedDetail()
        } else if(authored != null) {
            displayChallengeAuthoredDetail()
        }
    }

    private fun displayChallengeAuthoredDetail() {


    }

    private fun displayChallengeCompletedDetail() {


    }
}
