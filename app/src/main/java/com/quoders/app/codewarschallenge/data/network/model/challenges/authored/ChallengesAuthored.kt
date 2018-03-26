package com.quoders.app.codewarschallenge.data.network.model.challenges.authored

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ChallengesAuthored : Serializable {

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

}
