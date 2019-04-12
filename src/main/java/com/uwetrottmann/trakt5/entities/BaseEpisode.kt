package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

data class BaseEpisode(
    var number: Int? = null,

    /** collection  */
    var collected_at: OffsetDateTime? = null,
    /** watched  */
    var plays: Int? = null,
    var last_watched_at: OffsetDateTime? = null,
    /** progress  */
    var completed: Boolean? = null
)
