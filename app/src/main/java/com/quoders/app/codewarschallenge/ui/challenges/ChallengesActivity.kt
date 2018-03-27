package com.quoders.app.codewarschallenge.ui.challenges

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.quoders.app.codewarschallenge.CodewarsApplication
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum
import com.quoders.app.codewarschallenge.ui.detail.ChallengeDetailActivity
import kotlinx.android.synthetic.main.activity_challenges.*
import java.util.*

class ChallengesActivity : AppCompatActivity(),
        ChallengeCompletedClickListener, ChallengeAuthoredClickListener {

    private lateinit var challengesCompletedAdapter: ChallengesCompletedAdapter
    private lateinit var challengesAuthoredAdapter: ChallengesAuthoredAdapter
    private lateinit var challengesViewModel: ChallengesViewModel
    lateinit var userName: String

    companion object {
        const val USER_NAME_INTENT_EXTRA = "ChallengesActivity.USER_NAME_INTENT_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)

        userName = intent.getStringExtra(USER_NAME_INTENT_EXTRA)
        initWidgets()
        initViewModel(userName)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_challenges_authored -> {
                recyclerViewChallenges.adapter = challengesAuthoredAdapter
                setNoChallengesVisibility(challengesAuthoredAdapter.itemCount)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_challenges_completed-> {
                recyclerViewChallenges.adapter = challengesCompletedAdapter
                setNoChallengesVisibility(challengesCompletedAdapter.itemCount)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setNoChallengesVisibility(itemCount: Int) {
        when (itemCount) {
            0 -> textViewEmptyChallenges.visibility = VISIBLE
            else -> textViewEmptyChallenges.visibility = GONE
        }
    }

    private fun initWidgets() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        initialiseCompletedAdapter()
        initialiseAuthoredAdapter()
        initialiseRecyclerView()
    }

    private fun initialiseRecyclerView() {
        recyclerViewChallenges.layoutManager = LinearLayoutManager(this)
        recyclerViewChallenges.adapter = challengesCompletedAdapter
        recyclerViewChallenges.itemAnimator = DefaultItemAnimator()
    }

    private fun initialiseAuthoredAdapter() {
        val challengesAuthored: List<com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum> = ArrayList()
        challengesAuthoredAdapter = ChallengesAuthoredAdapter(challengesAuthored, this)
    }

    private fun initialiseCompletedAdapter() {
        val challenges: MutableList<Datum> = ArrayList()
        challengesCompletedAdapter = ChallengesCompletedAdapter(challenges, this)
        challengesCompletedAdapter.setEndOfListListener(object : OnEndOfListListener {
            override fun onEndOfListView(position: Int) {
                requestMoreChallenges()
            }
        })
    }

    private fun requestMoreChallenges() {
        if(!challengesViewModel.getNextChallengeCompletedPage()) {
            Toast.makeText(this, R.string.toast_no_more_challenges, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, R.string.toast_loading_challenges, Toast.LENGTH_SHORT).show()
            progressBar.show()
        }
    }

    private fun initViewModel(userName: String?) {
        challengesViewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
        challengesViewModel.init(userName!!, (application as CodewarsApplication).repository)
        observeChallengesCompleted()
        observeChallengesAuthored()
    }

    private fun observeChallengesAuthored() {
        challengesViewModel.challengesAuthored.observe(this, Observer {
            challenges ->
            progressBar.hide()
            when(challenges?.data) {
                null -> displayErrorDialog(R.string.dialog_error_message_loading_challenges_authored)
                else -> challengesAuthoredAdapter.setItems(challenges.data)
            }
        })
    }

    private fun observeChallengesCompleted() {
        challengesViewModel.challengesCompleted.observeForever({
            challenges ->
            progressBar.hide()
            when(challenges?.data) {
                null -> displayErrorDialog(R.string.dialog_error_message_loading_challenges_completed)
                else -> challengesCompletedAdapter.addItems(challenges.data!!)
            }
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

    override fun onChallengeItemlClick(challenge: Datum) {
        val intent = Intent(this, ChallengeDetailActivity::class.java)
        intent.putExtra(ChallengeDetailActivity.INTENT_EXTRA_CHALLENGE_COMPLETED, challenge)
        startActivity(intent)
    }

    override fun onChallengeItemlClick(challenge: com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum) {
        val intent = Intent(this, ChallengeDetailActivity::class.java)
        intent.putExtra(ChallengeDetailActivity.INTENT_EXTRA_CHALLENGE_AUTHORED, challenge)
        startActivity(intent)
    }
}
