package com.uwetrottmann.trakt5.enums

import java.util.HashMap
import java.util.Locale

enum class SortHow constructor(private val value: String) {
    ASC("asc"),
    DESC("desc");

    override fun toString(): String {
        return value
    }

    companion object {

        private val STRING_MAPPING = HashMap<String, SortHow>()

        init {
            for (via in values()) {
                STRING_MAPPING[via.toString().toUpperCase(Locale.ROOT)] = via
            }
        }

        fun fromValue(value: String): SortHow {
            return STRING_MAPPING[value.toUpperCase(Locale.ROOT)] ?: ASC
        }
    }

}
