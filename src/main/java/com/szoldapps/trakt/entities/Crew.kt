package com.szoldapps.trakt.entities

import com.google.gson.annotations.SerializedName

data class Crew(

    var writing: List<CrewMember>? = null,
    var production: List<CrewMember>? = null,
    var directing: List<CrewMember>? = null,
    @SerializedName("costume & make-up")
    var costumeAndMakeUp: List<CrewMember>? = null,
    var art: List<CrewMember>? = null,
    var sound: List<CrewMember>? = null,
    var camera: List<CrewMember>? = null

)
