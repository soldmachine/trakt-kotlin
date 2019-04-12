package com.uwetrottmann.trakt5.entities

data class SyncPerson(
    var ids: PersonIds? = null,
    var name: String? = null
) {

    fun id(id: PersonIds?): SyncPerson? {
        this.ids = id
        return this
    }

    fun name(name: String?): SyncPerson? {
        this.name = name
        return this
    }

}
