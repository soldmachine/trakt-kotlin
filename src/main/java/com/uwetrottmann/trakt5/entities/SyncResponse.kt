package com.uwetrottmann.trakt5.entities

data class SyncResponse(
    var added: SyncStats? = null,
    var existing: SyncStats? = null,
    var deleted: SyncStats? = null,
    var not_found: SyncErrors? = null
)
