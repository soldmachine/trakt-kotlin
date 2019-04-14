package com.szoldapps.trakt.entities

data class SyncStats(
    var movies: Int? = null,
    var shows: Int? = null,
    var seasons: Int? = null,
    var episodes: Int? = null,
    var people: Int? = null
)
