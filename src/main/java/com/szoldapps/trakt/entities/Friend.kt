package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class Friend(

    var friends_at: OffsetDateTime? = null,
    var user: User? = null

)
