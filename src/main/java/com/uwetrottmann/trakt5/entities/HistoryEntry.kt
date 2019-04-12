package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

data class HistoryEntry(

    var id: Long? = null,
    var watched_at: OffsetDateTime? = null,
    var action: String? = null,
    var type: String? = null,

    var episode: Episode? = null,
    var show: Show? = null,

    var movie: Movie? = null

)
