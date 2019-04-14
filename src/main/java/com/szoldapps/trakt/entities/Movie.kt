package com.szoldapps.trakt.entities

import org.threeten.bp.LocalDate

data class Movie(
    var year: Int? = null,
    var ids: MovieIds? = null,

    // extended info
    var certification: String? = null,
    var tagline: String? = null,
    /** Date in UTC time.  */
    var released: LocalDate? = null,
    var runtime: Int? = null,
    var trailer: String? = null,
    var homepage: String? = null,
    var language: String? = null,
    var genres: List<String>? = null
) : BaseEntity() 
