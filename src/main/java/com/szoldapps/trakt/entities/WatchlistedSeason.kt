package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class WatchlistedSeason(
    var listed_at: OffsetDateTime? = null,

    var season: Season? = null,
    var show: Show? = null
)
