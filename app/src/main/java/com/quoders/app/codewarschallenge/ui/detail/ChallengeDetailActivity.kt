package com.quoders.app.codewarschallenge.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum
import kotlinx.android.synthetic.main.activity_challenge_detail.*

class ChallengeDetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_EXTRA_CHALLENGE_COMPLETED = "ChallengeDetailActivity.INTENT_EXTRA_CHALLENGE_COMPLETED"
        const val INTENT_EXTRA_CHALLENGE_AUTHORED = "ChallengeDetailActivity.INTENT_EXTRA_CHALLENGE_AUTHORED"
    }

    var completed: Datum? = null
    var authored: com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)

        completed = intent.getSerializableExtra(INTENT_EXTRA_CHALLENGE_COMPLETED)
                        as? com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum

        authored = intent.getSerializableExtra(INTENT_EXTRA_CHALLENGE_AUTHORED)
                        as? com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum

        if(completed != null) {
            displayChallengeCompletedDetail()
        } else if(authored != null) {
            displayChallengeAuthoredDetail()
        }
    }

    private fun displayChallengeCompletedDetail() {
        val challengeDetails: String = "Id:\n${completed?.id}\n\nName:\n${completed?.name}\n\n" +
                "Completed At:\n${completed?.completedAt}\n\nCompleted Languages:\n${getLanguages(completed?.completedLanguages)}"

        textViewDetail.text = challengeDetails
    }

    private fun getLanguages(languages: List<String>?): String {
        val builder = StringBuilder()
        languages?.forEach { string -> builder.append(string + "\n") }
        return builder.toString()
    }

    private fun displayChallengeAuthoredDetail() {
        val challengeDetails: String = "Id:\n${authored?.id}\n\nName:\n${authored?.name}\n\n" +
                "Rank:\n${authored?.rank}\n\nDescription:\n${authored?.description}"

        textViewDetail.text = challengeDetails
    }
}
