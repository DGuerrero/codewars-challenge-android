package com.quoders.app.codewarschallenge.ui.challenges

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum

class ChallengesCompletedAdapter(private var mValues: List<Datum>,
                                 private val mListener: ChallengeCompletedClickListener?)
    : RecyclerView.Adapter<ChallengesCompletedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_challenge_completed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mTvName.text = mValues[position].name
        holder.mTvSlug.text = mValues[position].slug
        holder.mTvDate.text = mValues[position].completedAt

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
        val mTvName: TextView = mView.findViewById(R.id.textViewChallengeName)
        val mTvSlug: TextView = mView.findViewById(R.id.textViewChallengeSlug)
        val mTvDate: TextView = mView.findViewById(R.id.textViewChallengeDate)
        var mItem: Datum? = null
    }
}
