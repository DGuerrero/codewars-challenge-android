package com.quoders.app.codewarschallenge.ui.challenges

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.quoders.app.codewarschallenge.R
import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.Datum

class ChallengesCompletedAdapter(private var mValues: MutableList<Datum>,
                                 private val mListener: ChallengeCompletedClickListener?)
    : RecyclerView.Adapter<ChallengesCompletedAdapter.ViewHolder>() {

    private lateinit var endListListener: OnEndOfListListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_challenge, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = mValues[position]
        holder.tvTitle.text = mValues[position].name
        holder.tvSubtitle.text = mValues[position].slug
        holder.tvInfo.text = mValues[position].completedAt

        holder.mView.setOnClickListener { _ ->
            mListener?.onChallengeItemlClick(holder.item!!)
        }

        if(position == mValues.size-1) {
            endListListener.onEndOfListView(position)
        }
    }

    fun setEndOfListListener(listener: (OnEndOfListListener)) {
        endListListener = listener
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tvTitle: TextView = mView.findViewById(R.id.textViewChallengeTitle)
        val tvSubtitle: TextView = mView.findViewById(R.id.textViewChallengeSubtitle)
        val tvInfo: TextView = mView.findViewById(R.id.textViewChallengeInfo)
        var item: Datum? = null
    }

    fun addItems(data: List<Datum>) {
        mValues.addAll(data)
        notifyDataSetChanged()
    }
}
