package com.uwetrottmann.trakt5.enums

enum class Type constructor(private val value: String) : TraktEnum {

    MOVIE("movie"),
    SHOW("show"),
    EPISODE("episode"),
    PERSON("person"),
    LIST("list");

    override fun toString(): String {
        return value
    }

}
