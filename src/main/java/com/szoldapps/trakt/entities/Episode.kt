package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class Episode(
    var season: Int? = null,
    var number: Int? = null,
    var ids: EpisodeIds? = null,

    // extended info
    var number_abs: Int? = null,
    var first_aired: OffsetDateTime? = null,
    var comment_count: Int? = null,
    var runtime: Int? = null
) : BaseEntity()

