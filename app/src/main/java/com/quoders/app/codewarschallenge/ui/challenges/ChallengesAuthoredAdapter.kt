package com.quoders.app.codewarschallenge.ui.challenges

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.network.model.challenges.authored.Datum

class ChallengesAuthoredAdapter(private var mValues: List<Datum>,
                                private val mListener: ChallengeAuthoredClickListener?)
    : RecyclerView.Adapter<ChallengesAuthoredAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_challenge, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mTvTitle.text = mValues[position].name
        holder.mTvSubtitle.text = mValues[position].rankName
        holder.mTvInfo.text = mValues[position].rank.toString()

        holder.mView.setOnClickListener { _ ->
            mListener?.onChallengeItemlClick(holder.mItem!!)
        }
    }

    fun setItems(resultValues: List<Datum>?) {
        mValues = resultValues!!
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTvTitle: TextView = mView.findViewById(R.id.textViewChallengeTitle)
        val mTvSubtitle: TextView = mView.findViewById(R.id.textViewChallengeSubtitle)
        val mTvInfo: TextView = mView.findViewById(R.id.textViewChallengeInfo)
        var mItem: Datum? = null
    }
}
