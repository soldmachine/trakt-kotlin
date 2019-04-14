package com.szoldapps.trakt.entities

import com.google.gson.annotations.SerializedName

data class Account(

    @SerializedName("timezone")
    val timezone: String? = null,

    @SerializedName("date_format")
    val dateFormat: String? = null,

    @SerializedName("time_24hr")
    val time24Hr: Boolean? = null,

    @SerializedName("cover_image")
    val coverImage: String? = null

)
