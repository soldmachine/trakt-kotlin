package com.uwetrottmann.trakt5.entities

data class TrendingMovie(
    var movie: Movie? = null
) : BaseTrendingEntity()
