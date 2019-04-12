package com.uwetrottmann.trakt5.entities

import com.uwetrottmann.trakt5.enums.ListPrivacy
import com.uwetrottmann.trakt5.enums.SortBy
import com.uwetrottmann.trakt5.enums.SortHow
import org.threeten.bp.OffsetDateTime

data class TraktList(
    var ids: ListIds? = null,
    var name: String? = null,
    var description: String? = null,
    var privacy: ListPrivacy? = null,
    var display_numbers: Boolean? = null,
    var allow_comments: Boolean? = null,
    var sort_by: SortBy? = null,
    var sort_how: SortHow? = null,
    var created_at: OffsetDateTime? = null,
    var updated_at: OffsetDateTime? = null,
    var item_count: Int? = null,
    var comment_count: Int? = null,
    var likes: Int? = null,
    var user: User? = null
) {

    fun id(id: ListIds?): TraktList? {
        this.ids = id
        return this
    }

    fun name(name: String?): TraktList? {
        this.name = name
        return this
    }

    fun description(description: String?): TraktList? {
        this.description = description
        return this
    }

    fun privacy(privacy: ListPrivacy?): TraktList? {
        this.privacy = privacy
        return this
    }

    fun displayNumbers(displayNumbers: Boolean): TraktList? {
        this.display_numbers = displayNumbers
        return this
    }

    fun allowComments(allowComments: Boolean): TraktList? {
        this.allow_comments = allowComments
        return this
    }

    fun sortBy(sortBy: SortBy?): TraktList? {
        this.sort_by = sortBy
        return this
    }

    fun sortHow(sortHow: SortHow?): TraktList? {
        this.sort_how = sortHow
        return this
    }

}
