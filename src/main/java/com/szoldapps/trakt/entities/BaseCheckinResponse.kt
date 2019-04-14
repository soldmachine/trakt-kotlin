package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

abstract class BaseCheckinResponse {
    var watched_at: OffsetDateTime? = null
    var sharing: ShareSettings? = null
}
