package com.szoldapps.trakt.entities

data class PersonIds(
    var slug: String? = null,
    var tvrage: String? = null
) : BaseIds() {

    companion object {

        fun trakt(trakt: Int): PersonIds? {
            val ids = PersonIds()
            ids.trakt = trakt
            return ids
        }

        fun imdb(imdb: String?): PersonIds? {
            val ids = PersonIds()
            ids.imdb = imdb
            return ids
        }

        fun tmdb(tmdb: Int): PersonIds? {
            val ids = PersonIds()
            ids.tmdb = tmdb
            return ids
        }

        fun slug(slug: String?): PersonIds? {
            val ids = PersonIds()
            ids.slug = slug
            return ids
        }
    }
}
