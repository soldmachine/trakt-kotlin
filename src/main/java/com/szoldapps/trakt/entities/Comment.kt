package com.szoldapps.trakt.entities

import org.threeten.bp.OffsetDateTime

data class Comment(
    var id: Int? = null,
    var parent_id: Int? = null,
    var created_at: OffsetDateTime? = null,
    var updated_at: OffsetDateTime? = null,
    var comment: String? = null,
    var spoiler: Boolean? = null,
    var review: Boolean? = null,
    var replies: Int? = null,
    var likes: Int? = null,
    var user_rating: Int? = null,
    var user: User? = null,

    // for posting
    var movie: Movie? = null,
    var show: Show? = null,
    var episode: Episode? = null
) {

    /**
     * Build a movie comment.
     */
    constructor(movie: Movie?, comment: String?, spoiler: Boolean, review: Boolean) : this(comment, spoiler, review) {
        this.movie = movie
    }

    /**
     * Build a show comment.
     */
    constructor(show: Show?, comment: String?, spoiler: Boolean, review: Boolean) : this(comment, spoiler, review) {
        this.show = show
    }

    /**
     * Build an episode comment.
     */
    constructor(episode: Episode?, comment: String?, spoiler: Boolean, review: Boolean) : this(
        comment, spoiler, review
    ) {
        this.episode = episode
    }

    /**
     * Build an updated comment.
     */
    constructor(comment: String?, spoiler: Boolean, review: Boolean) : this() {
        this.comment = comment
        this.spoiler = spoiler
        this.review = review
    }

}
