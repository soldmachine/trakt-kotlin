package com.szoldapps.trakt.entities

data class Ratings(
    var rating: Double? = null,
    var votes: Int? = null,
    var distribution: Map<String, Int>? = null
)
