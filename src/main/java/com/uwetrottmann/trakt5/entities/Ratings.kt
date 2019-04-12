package com.uwetrottmann.trakt5.entities

data class Ratings(
    var rating: Double? = null,
    var votes: Int? = null,
    var distribution: Map<String, Int>? = null
)
