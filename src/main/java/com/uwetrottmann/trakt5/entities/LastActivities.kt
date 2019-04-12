package com.uwetrottmann.trakt5.entities

import org.threeten.bp.OffsetDateTime

data class LastActivities(

    var all: OffsetDateTime? = null,
    var movies: LastActivityMore? = null,
    var episodes: LastActivityMore? = null,
    var shows: LastActivity? = null,
    var seasons: LastActivity? = null,
    var lists: ListsLastActivity? = null

)
