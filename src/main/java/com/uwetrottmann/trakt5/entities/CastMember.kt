package com.uwetrottmann.trakt5.entities

data class CastMember(

    var character: String? = null,
    var movie: Movie? = null,
    var show: Show? = null,
    var person: Person? = null

)
