package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class Follower(

    var followed_at: OffsetDateTime? = null,
    var user: User? = null

)
