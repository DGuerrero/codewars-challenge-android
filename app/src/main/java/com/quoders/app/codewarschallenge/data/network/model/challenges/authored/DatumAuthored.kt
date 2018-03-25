package com.quoders.app.codewarschallenge.data.network.model.challenges.authored

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DatumAuthored {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("rankName")
    @Expose
    var rankName: String? = null
    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null
    @SerializedName("languages")
    @Expose
    var languages: List<String>? = null

}
