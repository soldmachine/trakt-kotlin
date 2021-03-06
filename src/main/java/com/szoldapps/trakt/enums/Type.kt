package com.szoldapps.trakt.enums

enum class Type constructor(private val value: String) {

    MOVIE("movie"),
    SHOW("show"),
    EPISODE("episode"),
    PERSON("person"),
    LIST("list");

    override fun toString(): String {
        return value
    }

}
