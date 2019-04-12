package com.uwetrottmann.trakt5.entities

data class SearchResult(

    var type: String? = null,
    var score: Double? = null,
    var movie: Movie? = null,
    var show: Show? = null,
    var episode: Episode? = null,
    var person: Person? = null,
    var list: TraktList? = null

)
