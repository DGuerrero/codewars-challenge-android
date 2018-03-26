package com.quoders.app.codewarschallenge.ui.search

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity

class SearchAdapter(private var mValues: List<UserEntity>, private val mListener: UserItemClickListener?)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mTvUserName.text = "Name: ${mValues[position].name}"
        holder.mTvUserRank.text = "Rank: ${mValues[position].rank}"
        holder.mTvLeaderboardPosition.text = "Leaderboard position: ${mValues[position].leaderboardPosition}"

        holder.mView.setOnClickListener { _ ->
            mListener?.onUserItemlClick(holder.mItem!!)
        }
    }

    fun setItems(resultValues: List<UserEntity>?) {
        mValues = resultValues!!
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTvUserName: TextView = mView.findViewById(R.id.textViewUserName)
        val mTvUserRank: TextView = mView.findViewById(R.id.textViewRank)
        val mTvLeaderboardPosition: TextView = mView.findViewById(R.id.textViewLeaderboardPosition)
        var mItem: UserEntity? = null
    }
}
