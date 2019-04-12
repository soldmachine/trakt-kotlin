package com.uwetrottmann.trakt5.entities

data class EpisodeCheckinResponse(
    var episode: Episode? = null,
    var show: Show? = null
) : BaseCheckinResponse()
