package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class Followed(

    var approved_at: OffsetDateTime? = null,
    var user: User? = null

)
