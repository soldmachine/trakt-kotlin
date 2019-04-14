package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class BaseMovie(
    var movie: Movie? = null,

    var collected_at: OffsetDateTime? = null,
    var last_watched_at: OffsetDateTime? = null,
    var listed_at: OffsetDateTime? = null,
    var plays: Int = 0
)
