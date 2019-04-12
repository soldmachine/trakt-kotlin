package com.uwetrottmann.trakt5.enums

import java.util.HashMap
import java.util.Locale

enum class SortBy constructor(private val value: String) {

    RANK("rank"),
    ADDED("added"),
    TITLE("title"),
    RELEASED("released"),
    RUNTIME("runtime"),
    POPULARITY("popularity"),
    PERCENTAGE("percentage"),
    VOTES("votes"),
    MY_RATING("my_rating"),
    RANDOM("random");

    override fun toString(): String {
        return value
    }

    companion object {

        private val STRING_MAPPING = HashMap<String, SortBy>()

        init {
            for (via in SortBy.values()) {
                STRING_MAPPING[via.toString().toUpperCase(Locale.ROOT)] = via
            }
        }

        fun fromValue(value: String): SortBy {
            return STRING_MAPPING[value.toUpperCase(Locale.ROOT)] ?: RANDOM
        }
    }

}
