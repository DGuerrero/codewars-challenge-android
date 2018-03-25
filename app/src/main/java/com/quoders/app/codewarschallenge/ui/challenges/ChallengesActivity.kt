package com.quoders.app.codewarschallenge.ui.challenges

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.quoders.app.codewarschallenge.R
import kotlinx.android.synthetic.main.activity_challenges.*

class ChallengesActivity : AppCompatActivity() {

    lateinit var challengesViewModel: ChallengesViewModel
    lateinit var userName: String

    companion object {
        val USER_NAME_INTENT_EXTRA = "ChallengesActivity.USER_NAME_INTENT_EXTRA"
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_challenges_authored -> {
                message.setText("authored")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_challenges_completed-> {
                message.setText("completed")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        userName = intent.getStringExtra(USER_NAME_INTENT_EXTRA)
        challengesViewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
        challengesViewModel.init(userName)
        challengesViewModel.challengesCompleted.observe(this, Observer {
            //  Update UI
            //challenges -> challenges[1].data
        })
    }
}
