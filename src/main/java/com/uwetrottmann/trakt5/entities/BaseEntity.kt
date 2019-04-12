package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

abstract class BaseEntity {

    var title: String? = null

    // extended info
    var overview: String? = null
    var rating: Double? = null
    var votes: Int? = null
    var updated_at: OffsetDateTime? = null
    var available_translations: List<String>? = null

}
