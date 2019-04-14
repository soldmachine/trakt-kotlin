package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class BaseShow(

    var show: Show? = null,

    /** collection, watched  */
    var seasons: List<BaseSeason>? = null,

    /** collection  */
    var last_collected_at: OffsetDateTime? = null,
    /** watchlist  */
    var listed_at: OffsetDateTime? = null,
    /** watched  */
    var plays: Int? = null,
    var last_watched_at: OffsetDateTime? = null,
    /** progress  */
    var aired: Int? = null,
    var completed: Int? = null,
    var hidden_seasons: List<Season>? = null,
    var next_episode: Episode? = null

)
