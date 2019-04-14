package com.szoldapps.trakt.enums

enum class HistoryType constructor(private val value: String) {

    MOVIES("movies"),
    SHOWS("shows"),
    SEASONS("seasons"),
    EPISODES("episodes");

    override fun toString(): String {
        return value
    }

}
