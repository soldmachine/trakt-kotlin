package com.uwetrottmann.trakt5.entities

import com.google.gson.annotations.SerializedName

data class Account(

    @SerializedName("timezone")
    val timezone: String? = null,

    @SerializedName("date_format")
    val dateFormat: String? = null,

    @SerializedName("time_24hr")
    val time24hr: Boolean? = null,

    @SerializedName("cover_image")
    val coverImage: String? = null

)
