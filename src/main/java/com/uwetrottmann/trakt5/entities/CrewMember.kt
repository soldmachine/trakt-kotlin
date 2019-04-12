package com.uwetrottmann.trakt5.entities

data class CrewMember(

    var job: String? = null,
    var movie: Movie? = null,
    var show: Show? = null,
    var person: Person? = null

)
