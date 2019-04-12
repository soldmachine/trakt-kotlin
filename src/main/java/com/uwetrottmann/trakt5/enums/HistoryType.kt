package com.uwetrottmann.trakt5.enums

enum class HistoryType constructor(private val value: String) : TraktEnum {

    MOVIES("movies"),
    SHOWS("shows"),
    SEASONS("seasons"),
    EPISODES("episodes");

    override fun toString(): String {
        return value
    }

}
