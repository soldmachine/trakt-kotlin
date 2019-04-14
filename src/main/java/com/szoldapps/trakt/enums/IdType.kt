package com.szoldapps.trakt.enums

enum class IdType constructor(private val value: String) {

    TRAKT("trakt"),
    IMDB("imdb"),
    TMDB("tmdb"),
    TVDB("tvdb"),
    TVRAGE("tvrage");

    override fun toString(): String {
        return value
    }

}
