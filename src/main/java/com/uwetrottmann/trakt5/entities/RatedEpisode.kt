package com.uwetrottmann.trakt5.entities

data class RatedEpisode(
    var episode: Episode? = null
) : RatedShow()
