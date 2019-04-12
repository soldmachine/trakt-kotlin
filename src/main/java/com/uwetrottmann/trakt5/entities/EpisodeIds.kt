package com.uwetrottmann.trakt5.entities

data class EpisodeIds(
    var tvdb: Int? = null,
    var tvrage: Int? = null
) : BaseIds() {

    companion object {

        fun trakt(trakt: Int): EpisodeIds? {
            val ids = EpisodeIds()
            ids.trakt = trakt
            return ids
        }

        fun imdb(imdb: String?): EpisodeIds? {
            val ids = EpisodeIds()
            ids.imdb = imdb
            return ids
        }

        fun tmdb(tmdb: Int): EpisodeIds? {
            val ids = EpisodeIds()
            ids.tmdb = tmdb
            return ids
        }

        fun tvdb(tvdb: Int): EpisodeIds? {
            val ids = EpisodeIds()
            ids.tvdb = tvdb
            return ids
        }

        fun tvrage(tvrage: Int): EpisodeIds? {
            val ids = EpisodeIds()
            ids.tvrage = tvrage
            return ids
        }
    }

}
