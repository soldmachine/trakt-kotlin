package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class WatchlistedEpisode(
    var listed_at: OffsetDateTime? = null,

    var episode: Episode? = null,
    var show: Show? = null
) 
