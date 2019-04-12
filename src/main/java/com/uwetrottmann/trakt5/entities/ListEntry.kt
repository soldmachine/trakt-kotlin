package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

data class ListEntry(

    var id: Long? = null,
    var rank: Int? = null,
    var listed_at: OffsetDateTime? = null,
    var type: String? = null,
    var movie: Movie? = null,
    var show: Show? = null,
    var episode: Episode? = null,
    var person: Person? = null

)
