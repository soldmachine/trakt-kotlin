package com.szoldapps.trakt.enums

import java.util.HashMap

enum class ListPrivacy constructor(val value: String) {

    PRIVATE("private"),
    FRIENDS("friends"),
    PUBLIC("public");

    override fun toString(): String {
        return value
    }

    companion object {

        private val STRING_MAPPING = HashMap<String, ListPrivacy>()

        init {
            for (via in values()) {
                STRING_MAPPING[via.toString()] = via
            }
        }

        fun fromValue(value: String): ListPrivacy {
            return STRING_MAPPING[value] ?: PRIVATE
        }
    }
}
