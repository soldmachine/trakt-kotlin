package com.szoldapps.trakt.entities

import org.threeten.bp.LocalDate

data class CalendarMovieEntry(

    /** Date in UTC time.  */
    var released: LocalDate? = null,
    var movie: Movie? = null

)
