package com.quoders.app.codewarschallenge.data.network.model.challenges.completed

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ChallengesCompleted : Serializable {

    @SerializedName("totalPages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("totalItems")
    @Expose
    var totalItems: Int? = null
    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

}
