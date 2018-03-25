package com.quoders.app.codewarschallenge.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import com.quoders.app.codewarschallenge.CodewarsApplication
import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity
import kotlinx.android.synthetic.main.activity_search.*
import java.util.ArrayList

class SearchActivity : AppCompatActivity(), SearchContract.View, UserItemClickListener {

    lateinit var presenter: SearchContract.Presenter
    lateinit var searchAdapter: SearchAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var buttonSearch: Button
    lateinit var etSearchName: EditText
    

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

        val users: List<UserEntity> = ArrayList()
        searchAdapter = SearchAdapter(users, this)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(searchAdapter)
        recyclerView.setItemAnimator(DefaultItemAnimator())
    }

    override fun showProgressBar() {
        progressBar.visibility = VISIBLE
    }

    override fun dismissProgressBar() {
        progressBar.visibility = GONE
    }

    override fun showEmptySearchView() {

    }

    override fun showUserNotFound() {
        val simpleAlert = AlertDialog.Builder(this).create()
        simpleAlert.setTitle(R.string.dialog_error)
        simpleAlert.setMessage(getString(R.string.user_not_found))
        simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", {
            _,_ ->
        })
        simpleAlert.show()
    }

    override fun showLastSearchView() {

    }

    override fun updateUsersSearchList(usersEntity: List<UserEntity>) {

    }

    override fun observeUsersSearch(usersSearch: LiveData<List<UserEntity>>) {
        usersSearch.observe(this, Observer {
            users -> searchAdapter.setItems(users)
        })
    }

    override fun onUserItemlClick(user: UserEntity) {
        presenter.onUserClicked(user)
    }

    override fun launchChallengesView(userEntity: UserEntity) {

    }
}
