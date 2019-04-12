package com.uwetrottmann.trakt5.enums

enum class IdType constructor(private val value: String) : TraktEnum {

    TRAKT("trakt"),
    IMDB("imdb"),
    TMDB("tmdb"),
    TVDB("tvdb"),
    TVRAGE("tvrage");

    override fun toString(): String {
        return value
    }

}
