package com.szoldapps.trakt.entities

data class MovieCheckinResponse(
    var movie: Movie? = null
) : BaseCheckinResponse()
