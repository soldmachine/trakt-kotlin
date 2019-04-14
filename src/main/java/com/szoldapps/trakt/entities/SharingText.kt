package com.szoldapps.trakt.entities

import com.google.gson.annotations.SerializedName

data class SharingText(

    @SerializedName("watching")
    val watching: String? = null,

    @SerializedName("watched")
    val watched: String? = null

)
