package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class Season(

    var number: Int? = null,
    var ids: SeasonIds? = null,

    var title: String? = null,
    var overview: String? = null,
    var network: String? = null,
    var first_aired: OffsetDateTime? = null,
    var rating: Double? = null,
    var votes: Int? = null,
    var episode_count: Int? = null,
    var aired_episodes: Int? = null,
    var episodes: List<Episode>? = null
)
