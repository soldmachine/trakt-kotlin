package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

data class LastActivityMore(
    var watched_at: OffsetDateTime? = null,
    var collected_at: OffsetDateTime? = null,
    var paused_at: OffsetDateTime? = null,
    var hidden_at: OffsetDateTime? = null
) : LastActivity()
