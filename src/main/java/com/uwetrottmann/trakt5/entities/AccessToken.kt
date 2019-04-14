package com.uwetrottmann.trakt5.entities

import com.google.gson.annotations.SerializedName

data class AccessToken(

    @SerializedName("access_token")
    val accessToken: String? = null,

    @SerializedName("token_type")
    val tokenType: String? = null,

    @SerializedName("expires_in")
    val expiresIn: Int? = null,

    @SerializedName("refresh_token")
    val refreshToken: String? = null,

    @SerializedName("scope")
    val scope: String? = null

)
