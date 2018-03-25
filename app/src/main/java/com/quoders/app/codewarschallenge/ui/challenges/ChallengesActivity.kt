package com.quoders.app.codewarschallenge.ui.challenges

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.quoders.app.codewarschallenge.CodewarsApplication
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum
import kotlinx.android.synthetic.main.activity_challenges.*
import java.util.*

class ChallengesActivity : AppCompatActivity(), ChallengeCompletedClickListener, ChallengeAuthoredClickListener {

    private lateinit var progressBar: ContentLoadingProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var challengesCompletedAdapter: ChallengesCompletedAdapter
    private lateinit var challengesAuthoredAdapter: ChallengesAuthoredAdapter
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
                recyclerView.adapter = challengesAuthoredAdapter
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_challenges_completed-> {
                recyclerView.adapter = challengesCompletedAdapter
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
        challengesCompletedAdapter = ChallengesCompletedAdapter(challenges, this)
        val challengesAuthored: List<com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum> = ArrayList()
        challengesAuthoredAdapter = ChallengesAuthoredAdapter(challengesAuthored, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = challengesCompletedAdapter
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun initViewModels(userName: String?) {
        challengesViewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
        challengesViewModel.init(userName!!, (application as CodewarsApplication).repository)
        observeChallengesCompleted()
        observeChallengesAuthored()
    }

    private fun observeChallengesAuthored() {
        challengesViewModel.challengesAuthored.observe(this, Observer {
            challenges -> challengesAuthoredAdapter.setItems(challenges!!.data)
            /*when(challenges!!.datumAuthoreds) {
                null -> displayErrorDialog(R.string.dialog_error_message_loading_challenges_authored)
                else -> challengesAuthoredAdapter.setItems(challenges.datumAuthoreds)
            }*/
            progressBar.hide()
        })
    }

    private fun observeChallengesCompleted() {
        challengesViewModel.challengesCompleted.observe(this, Observer {
            challenges -> challengesCompletedAdapter.setItems(challenges!!.data)
            /*when(challenges!!.data) {
                null -> displayErrorDialog(R.string.dialog_error_message_loading_challenges_completed)
                else -> challengesCompletedAdapter.setItems(challenges.data)
            }*/
            progressBar.hide()
        })
    }

    private fun displayErrorDialog(message: Int) {
        val simpleAlert = AlertDialog.Builder(this).create()
        simpleAlert.setTitle(R.string.dialog_error_title)
        simpleAlert.setMessage(getString(message))
        simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
            _,_ ->
        })
        simpleAlert.show()
    }

    override fun onChallengeItemlClick(user: Datum) {
    }

    override fun onChallengeItemlClick(user: com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum) {
    }
}
