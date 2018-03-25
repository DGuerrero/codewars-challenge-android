package com.quoders.app.codewarschallenge.ui.challenges

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.quoders.app.codewarschallenge.CodewarsApplication
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum
import kotlinx.android.synthetic.main.activity_challenges.*
import java.util.*

class ChallengesActivity : AppCompatActivity(), ChallengeCompletedClickListener {

    private lateinit var progressBar: ContentLoadingProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var challengesAdapter: ChallengesCompletedAdapter
    lateinit var challengesViewModel: ChallengesViewModel
    lateinit var userName: String

    companion object {
        val USER_NAME_INTENT_EXTRA = "ChallengesActivity.USER_NAME_INTENT_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)

        userName = intent.getStringExtra(USER_NAME_INTENT_EXTRA)
        initWidgets()
        initViewModels(userName)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_challenges_authored -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_challenges_completed-> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun initWidgets() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerViewChallengesPending)
        val challenges: List<Datum> = ArrayList()
        challengesAdapter = ChallengesCompletedAdapter(challenges, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = challengesAdapter
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun initViewModels(userName: String?) {
        challengesViewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
        challengesViewModel.init(userName!!, (application as CodewarsApplication).repository)
        challengesViewModel.challengesCompleted.observe(this, Observer {
            challenges -> challengesAdapter.setItems(challenges?.data)
            progressBar.hide()
        })
    }

    override fun onChallengeItemlClick(user: Datum) {

    }
}
