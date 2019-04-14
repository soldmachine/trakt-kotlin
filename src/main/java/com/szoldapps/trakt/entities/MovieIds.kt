package com.szoldapps.trakt.entities

data class MovieIds(
    var slug: String? = null
) : BaseIds() {

    companion object {

        fun trakt(trakt: Int): MovieIds? {
            val ids = MovieIds()
            ids.trakt = trakt
            return ids
        }

        fun imdb(imdb: String?): MovieIds? {
            val ids = MovieIds()
            ids.imdb = imdb
            return ids
        }

        fun tmdb(tmdb: Int): MovieIds? {
            val ids = MovieIds()
            ids.tmdb = tmdb
            return ids
        }

        fun slug(slug: String?): MovieIds? {
            val ids = MovieIds()
            ids.slug = slug
            return ids
        }
    }

}
