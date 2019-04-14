package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class CalendarShowEntry(

    var first_aired: OffsetDateTime? = null,
    var episode: Episode? = null,
    var show: Show? = null

)
