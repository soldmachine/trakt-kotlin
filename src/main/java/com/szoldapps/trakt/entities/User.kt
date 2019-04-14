package com.szoldapps.trakt.entities

import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime

data class User(

    var username: String? = null,
    @SerializedName("private")
    var isPrivate: Boolean? = null,
    var name: String? = null,
    /** If a user is a regular VIP.  */
    var vip: Boolean? = null,
    /** If a user is an execute producer.  */
    @SerializedName("vip_ep")
    var vipEp: Boolean? = null,
    var ids: UserIds? = null,

    // full
    @SerializedName("joined_at")
    var joinedAt: OffsetDateTime? = null,
    var location: String? = null,
    var about: String? = null,
    var gender: String? = null,
    var age: Int = 0,
    var images: Images? = null


)

data class UserIds(
    var slug: String? = null
)
