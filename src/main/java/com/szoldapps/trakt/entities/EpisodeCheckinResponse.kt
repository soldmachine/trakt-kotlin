package com.szoldapps.trakt.entities

data class EpisodeCheckinResponse(
    var episode: Episode? = null,
    var show: Show? = null
) : BaseCheckinResponse()
