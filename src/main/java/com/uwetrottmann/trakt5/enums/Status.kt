package com.uwetrottmann.trakt5.enums

import java.util.HashMap
import java.util.Locale

enum class Status constructor(private val value: String) : TraktEnum {

    ENDED("ended"),
    RETURNING("returning series"),
    CANCELED("canceled"),
    IN_PRODUCTION("in production");

    override fun toString(): String {
        return value
    }

    companion object {

        private val STRING_MAPPING = HashMap<String, Status>()

        init {
            for (via in values()) {
                STRING_MAPPING[via.toString().toUpperCase(Locale.ROOT)] = via
            }
        }

        fun fromValue(value: String): Status {
            return STRING_MAPPING[value.toUpperCase(Locale.ROOT)] ?: ENDED
        }
    }

}
