package com.uwetrottmann.trakt5.entities

data class ShowIds(
    var slug: String? = null,
    var tvdb: Int? = null,
    var tvrage: Int? = null
) : BaseIds() {

    companion object {

        fun trakt(trakt: Int): ShowIds? {
            val ids = ShowIds()
            ids.trakt = trakt
            return ids
        }

        fun imdb(imdb: String?): ShowIds? {
            val ids = ShowIds()
            ids.imdb = imdb
            return ids
        }

        fun tmdb(tmdb: Int): ShowIds? {
            val ids = ShowIds()
            ids.tmdb = tmdb
            return ids
        }

        fun slug(slug: String?): ShowIds? {
            val ids = ShowIds()
            ids.slug = slug
            return ids
        }

        fun tvdb(tvdb: Int): ShowIds? {
            val ids = ShowIds()
            ids.tvdb = tvdb
            return ids
        }

        fun tvrage(tvrage: Int): ShowIds? {
            val ids = ShowIds()
            ids.tvrage = tvrage
            return ids
        }
    }

}
