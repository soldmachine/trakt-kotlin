package com.uwetrottmann.trakt5.entities

import com.uwetrottmann.trakt5.enums.Rating
import org.threeten.bp.OffsetDateTime

data class SyncEpisode(
    var season: Int? = null,
    var number: Int? = null,
    var ids: EpisodeIds? = null,

    var collected_at: OffsetDateTime? = null,
    var watched_at: OffsetDateTime? = null,
    var rated_at: OffsetDateTime? = null,
    var rating: Rating? = null
) {

    fun number(number: Int): SyncEpisode? {
        this.number = number
        return this
    }

    fun season(season: Int): SyncEpisode? {
        this.season = season
        return this
    }

    fun id(id: EpisodeIds?): SyncEpisode? {
        this.ids = id
        return this
    }

    fun collectedAt(collectedAt: OffsetDateTime?): SyncEpisode? {
        this.collected_at = collectedAt
        return this
    }

    fun watchedAt(watchedAt: OffsetDateTime?): SyncEpisode? {
        this.watched_at = watchedAt
        return this
    }

    fun ratedAt(ratedAt: OffsetDateTime?): SyncEpisode? {
        this.rated_at = ratedAt
        return this
    }

    fun rating(rating: Rating?): SyncEpisode? {
        this.rating = rating
        return this
    }

}
