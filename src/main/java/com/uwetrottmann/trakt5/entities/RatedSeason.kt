package com.uwetrottmann.trakt5.entities

data class RatedSeason(
    var season: Season? = null
) : RatedShow()
