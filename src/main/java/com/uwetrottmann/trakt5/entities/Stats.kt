package com.uwetrottmann.trakt5.entities

data class Stats(

    var watchers: Int? = null,
    var plays: Int? = null,
    var collectors: Int? = null,
    var comments: Int? = null,
    var lists: Int? = null,
    var votes: Int? = null,

    /** Specific to shows, seasons and episodes.  */
    var collected_episodes: Int? = null

)
