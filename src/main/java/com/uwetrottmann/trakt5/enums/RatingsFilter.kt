package com.uwetrottmann.trakt5.enums

enum class RatingsFilter constructor(private val value: String) : TraktEnum {
    ALL(""),
    WEAKSAUCE("/1"),
    TERRIBLE("/2"),
    BAD("/3"),
    POOR("/4"),
    MEH("/5"),
    FAIR("/6"),
    GOOD("/7"),
    GREAT("/8"),
    SUPERB("/9"),
    TOTALLYNINJA("/10");

    override fun toString(): String {
        return this.value
    }

}
