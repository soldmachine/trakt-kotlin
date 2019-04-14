package com.szoldapps.trakt.entities

import com.google.gson.annotations.SerializedName

data class Connections(

    @SerializedName("facebook")
    val facebook: Boolean? = null,

    @SerializedName("twitter")
    val twitter: Boolean? = null,

    @SerializedName("google")
    val google: Boolean? = null,

    @SerializedName("tumblr")
    val tumblr: Boolean? = null,

    @SerializedName("medium")
    val medium: Boolean? = null,

    @SerializedName("slack")
    val slack: Boolean? = null

)
