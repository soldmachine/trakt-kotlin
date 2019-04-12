package com.uwetrottmann.trakt5.entities

data class AccessToken(
    var access_token: String? = null,
    var token_type: String? = null,
    var expires_in: Int? = null,
    var refresh_token: String? = null,
    var scope: String? = null
)
