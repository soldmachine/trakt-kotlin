package com.szoldapps.trakt.entities

data class ListReorderResponse(
    var updated: Int? = null,
    var skipped_ids: List<Long>? = null
)
