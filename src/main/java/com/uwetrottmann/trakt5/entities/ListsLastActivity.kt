package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

data class ListsLastActivity(
    var liked_at: OffsetDateTime? = null,
    var updated_at: OffsetDateTime? = null,
    var commented_at: OffsetDateTime? = null
)
