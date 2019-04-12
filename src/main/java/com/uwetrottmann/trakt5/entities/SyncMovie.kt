package com.uwetrottmann.trakt5.entities

import com.uwetrottmann.trakt5.enums.Rating
import org.threeten.bp.OffsetDateTime

data class SyncMovie(
    var ids: MovieIds? = null,

    var collected_at: OffsetDateTime? = null,
    var watched_at: OffsetDateTime? = null,
    var rated_at: OffsetDateTime? = null,
    var rating: Rating? = null
) {

    fun id(id: MovieIds?): SyncMovie? {
        this.ids = id
        return this
    }

    fun collectedAt(collectedAt: OffsetDateTime?): SyncMovie? {
        this.collected_at = collectedAt
        return this
    }

    fun watchedAt(watchedAt: OffsetDateTime?): SyncMovie? {
        this.watched_at = watchedAt
        return this
    }

    fun ratedAt(ratedAt: OffsetDateTime?): SyncMovie? {
        this.rated_at = ratedAt
        return this
    }

    fun rating(rating: Rating?): SyncMovie? {
        this.rating = rating
        return this
    }

}
