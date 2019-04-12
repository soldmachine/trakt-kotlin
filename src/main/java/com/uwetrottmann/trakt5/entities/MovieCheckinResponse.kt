package com.uwetrottmann.trakt5.entities

data class MovieCheckinResponse(
    var movie: Movie? = null
) : BaseCheckinResponse()
