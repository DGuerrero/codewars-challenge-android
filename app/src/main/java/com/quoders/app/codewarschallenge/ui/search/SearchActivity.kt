package com.quoders.app.codewarschallenge.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.quoders.app.codewarschallenge.CodewarsApplication
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import com.quoders.app.codewarschallenge.ui.challenges.ChallengesActivity
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

class SearchActivity : AppCompatActivity(), SearchContract.View, UserItemClickListener {

    private val MAX_ITEMS_TO_DISPLAY: Int = 5

    private lateinit var presenter: SearchContract.Presenter
    private lateinit var searchAdapter: SearchAdapter

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
        buttonSearch.setOnClickListener({ presenter.onSearchForUser(editTextSearchName.text.toString()) })

        val users: List<UserEntity> = ArrayList()
        searchAdapter = SearchAdapter(users, this)
        recyclerViewSearchResult.layoutManager = LinearLayoutManager(this)
        recyclerViewSearchResult.adapter = searchAdapter
        recyclerViewSearchResult.itemAnimator = DefaultItemAnimator()
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
            showUsersListView(users)
        })
    }

    private fun showUsersListView(users: List<UserEntity>?) {
        if(users?.isEmpty() == false) {
            recyclerViewSearchResult.visibility = VISIBLE
            linearLayoutEmptySearch.visibility = GONE
        }
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
        showUsersListView(usersEntity)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.order_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_order_rank) {
            presenter.onOrderByRankClick()
            return true
        } else {
            super.onOptionsItemSelected(item)
        }
        return false
    }
}
