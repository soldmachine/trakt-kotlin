package com.uwetrottmann.trakt5.entities

import com.uwetrottmann.trakt5.enums.Rating
import org.threeten.bp.OffsetDateTime

open class BaseRatedEntity {
    var rated_at: OffsetDateTime? = null
    var rating: Rating? = null
}
