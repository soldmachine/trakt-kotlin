package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class ListsLastActivity(
    var liked_at: OffsetDateTime? = null,
    var updated_at: OffsetDateTime? = null,
    var commented_at: OffsetDateTime? = null
)
