package com.uwetrottmann.trakt5.entities

data class BaseSeason(
    var number: Int? = null,
    var episodes: List<BaseEpisode>? = null,

    /** progress  */
    var aired: Int? = null,
    /** progress  */
    var completed: Int? = null
)
