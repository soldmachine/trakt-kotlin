package com.szoldapps.trakt.entities

import com.szoldapps.trakt.enums.Rating
import org.threeten.bp.OffsetDateTime

open class BaseRatedEntity {
    var rated_at: OffsetDateTime? = null
    var rating: Rating? = null
}
