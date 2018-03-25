package com.quoders.app.codewarschallenge.data.network.model.challenges.authored

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChallengesAuthored {

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

}
