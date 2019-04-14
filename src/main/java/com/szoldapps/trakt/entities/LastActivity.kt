package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

open class LastActivity {

    var rated_at: OffsetDateTime? = null
    var watchlisted_at: OffsetDateTime? = null
    var commented_at: OffsetDateTime? = null

}
