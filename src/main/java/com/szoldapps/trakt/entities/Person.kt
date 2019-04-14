package com.szoldapps.trakt.entities

import org.threeten.bp.LocalDate

data class Person(

    var name: String? = null,
    var ids: PersonIds? = null,

    // extended info
    var biography: String? = null,
    var birthday: LocalDate? = null,
    var death: LocalDate? = null,
    var birthplace: String? = null,
    var homepage: String? = null

)
