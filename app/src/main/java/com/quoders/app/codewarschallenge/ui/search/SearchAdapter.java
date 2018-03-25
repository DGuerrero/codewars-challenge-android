package com.quoders.app.codewarschallenge.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quoders.app.codewarschallenge.R;
import com.quoders.app.codewarschallenge.data.local.entities.UserEntity;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<UserEntity> mValues;
    private final UserItemClickListener mListener;

    public SearchAdapter(List<UserEntity> items, UserItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTvUserName.setText(mValues.get(position).getName());
        holder.mTvUserClan.setText(mValues.get(position).getClan());
        holder.mTvUserHonor.setText(String.valueOf(mValues.get(position).getHonor()));

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onUserItemlClick(holder.mItem);
            }
        });
    }

    void setItems(List<UserEntity> resultValues) {
        mValues = resultValues;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mTvUserName;
        final TextView mTvUserClan;
        final TextView mTvUserHonor;
        UserEntity mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mTvUserName = view.findViewById(R.id.textViewUserName);
            mTvUserClan = view.findViewById(R.id.textViewClan);
            mTvUserHonor = view.findViewById(R.id.textViewHonor);
        }
    }
}
