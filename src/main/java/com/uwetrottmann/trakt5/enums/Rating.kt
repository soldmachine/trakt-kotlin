package com.uwetrottmann.trakt5.enums

enum class Rating constructor(var value: Int) {

    WEAKSAUCE(1),
    TERRIBLE(2),
    BAD(3),
    POOR(4),
    MEH(5),
    FAIR(6),
    GOOD(7),
    GREAT(8),
    SUPERB(9),
    TOTALLYNINJA(10);

    override fun toString(): String {
        return value.toString()
    }

    companion object {

        fun fromValue(value: Int): Rating {
            return Rating.values()[value - 1]
        }
    }

}
