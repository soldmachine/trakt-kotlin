package com.uwetrottmann.trakt5.entities

data class Settings(

    var user: User? = null,
    var account: Account? = null,
    var connections: Connections? = null,
    var sharing_text: SharingText? = null

)
