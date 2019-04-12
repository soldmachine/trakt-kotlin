package com.uwetrottmann.trakt5.entities

data class SyncErrors(

    var movies: List<SyncMovie>? = null,
    var shows: List<SyncShow>? = null,
    var seasons: List<SyncSeason>? = null,
    var episodes: List<SyncEpisode>? = null,
    var people: List<SyncPerson>? = null,
    var ids: List<Long>? = null

)
