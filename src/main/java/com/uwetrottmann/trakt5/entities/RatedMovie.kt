package com.uwetrottmann.trakt5.entities

data class RatedMovie(
    var movie: Movie? = null
) : BaseRatedEntity()
