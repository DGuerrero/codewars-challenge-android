
package com.quoders.app.codewarschallenge.data.network.model.users;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CodeChallenges {

    @SerializedName("totalAuthored")
    private Long TotalAuthored;
    @SerializedName("totalCompleted")
    private Long TotalCompleted;

    public Long getTotalAuthored() {
        return TotalAuthored;
    }

    public void setTotalAuthored(Long totalAuthored) {
        TotalAuthored = totalAuthored;
    }

    public Long getTotalCompleted() {
        return TotalCompleted;
    }

    public void setTotalCompleted(Long totalCompleted) {
        TotalCompleted = totalCompleted;
    }

}
