package com.quoders.app.codewarschallenge.data.network.model.challenges.completed

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("completedLanguages")
    @Expose
    var completedLanguages: List<String>? = null
    @SerializedName("completedAt")
    @Expose
    var completedAt: String? = null

}
