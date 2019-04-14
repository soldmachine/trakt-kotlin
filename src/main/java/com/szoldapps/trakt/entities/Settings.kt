package com.szoldapps.trakt.entities

import com.google.gson.annotations.SerializedName

data class Settings(

    @SerializedName("user")
    val user: User? = null,

    @SerializedName("account")
    val account: Account? = null,

    @SerializedName("connections")
    val connections: Connections? = null,

    @SerializedName("sharing_text")
    val sharingText: SharingText? = null

)
