package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

data class Follower(

    var followed_at: OffsetDateTime? = null,
    var user: User? = null

)
