package com.uwetrottmann.trakt5.entities

data class ListReorderResponse(
    var updated: Int? = null,
    var skipped_ids: List<Long>? = null
)
