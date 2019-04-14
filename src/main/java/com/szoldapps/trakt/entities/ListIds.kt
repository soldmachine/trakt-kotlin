package com.szoldapps.trakt.entities

data class ListIds(
    var trakt: Int? = null,
    var slug: String? = null
) {

    companion object {

        fun trakt(trakt: Int): ListIds? {
            val ids = ListIds()
            ids.trakt = trakt
            return ids
        }

        fun slug(slug: String?): ListIds? {
            val ids = ListIds()
            ids.slug = slug
            return ids
        }
    }

}
