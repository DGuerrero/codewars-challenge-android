package com.quoders.app.codewarschallenge.ui.challenges;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.quoders.app.codewarschallenge.data.network.model.challenges.completed.ChallengesCompleted;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChallengesViewModel extends ViewModel {

    private LiveData<List<ChallengesCompleted>> challengesCompleted;
    private String userName;

    public LiveData<List<ChallengesCompleted>> getChallengesCompleted() {
        return challengesCompleted;
    }

    public void init(@Nullable String userName) {
        this.userName = userName;
    }
}
