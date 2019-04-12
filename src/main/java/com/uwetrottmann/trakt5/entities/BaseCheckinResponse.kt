package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

abstract class BaseCheckinResponse {
    var watched_at: OffsetDateTime? = null
    var sharing: ShareSettings? = null
}
