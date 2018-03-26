package com.quoders.app.codewarschallenge.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DialogTitle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import com.quoders.app.codewarschallenge.CodewarsApplication
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.ui.challenges.ChallengesActivity
import kotlinx.android.synthetic.main.activity_search.*
import java.util.ArrayList

class SearchActivity : AppCompatActivity(), SearchContract.View, UserItemClickListener {

    private val MAX_ITEMS_TO_DISPLAY: Int = 5

    private lateinit var presenter: SearchContract.Presenter
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonSearch: Button
    private lateinit var buttonOrderByRank: Button
    private lateinit var etSearchName: EditText
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initWidgets()
        initComponents()
    }

    private fun initComponents() {
        presenter = SearchPresenter(this, (application as CodewarsApplication).repository)
    }

    private fun initWidgets() {
        etSearchName = findViewById(R.id.editTextSearchName)
        recyclerView = findViewById(R.id.recyclerViewSearchResult)
        buttonSearch = findViewById(R.id.buttonSearch)
        buttonSearch.setOnClickListener({ presenter.onSearchForUser(etSearchName.text.toString()) })
        buttonOrderByRank = findViewById(R.id.buttonSearchOrderByRank)
        buttonOrderByRank.setOnClickListener({ presenter.onOrderByRankClick() })

        val users: List<UserEntity> = ArrayList()
        searchAdapter = SearchAdapter(users, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = searchAdapter
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun showProgressBar() {
        progressBar.visibility = VISIBLE
    }

    override fun dismissProgressBar() {
        progressBar.visibility = GONE
    }

    override fun showUserNotFound() {
        showAlertDialog(R.string.dialog_error_title, R.string.dialog_error_message_user_not_found)
    }

    override fun showGenericError() {
        showAlertDialog(R.string.dialog_error_title, R.string.dialog_error_message_generic)
    }

    override fun observeUsersSearch(usersSearch: LiveData<List<UserEntity>>) {
        usersSearch.observe(this, Observer {
            users ->
            searchAdapter.setItems(users?.sortedByDescending {
                userEntity -> userEntity.addedOn }?.take(MAX_ITEMS_TO_DISPLAY))

            searchAdapter.notifyDataSetChanged()
        })
    }

    override fun onUserItemlClick(user: UserEntity) {
        presenter.onUserClicked(user)
    }

    override fun launchChallengesView(user: UserEntity) {
        val intent = Intent(this, ChallengesActivity::class.java)
        intent.putExtra(ChallengesActivity.USER_NAME_INTENT_EXTRA, user.name)
        startActivity(intent)
    }

    override fun updateUsersSearchList(usersEntity: List<UserEntity>) {
        searchAdapter.setItems(usersEntity)
        searchAdapter.notifyDataSetChanged()
    }

    private fun showAlertDialog(title: Int, message: Int) {
        val simpleAlert = AlertDialog.Builder(this).create()
        simpleAlert.setTitle(title)
        simpleAlert.setMessage(getString(message))
        simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
            _,_ ->
        })
        simpleAlert.show()
    }
}
