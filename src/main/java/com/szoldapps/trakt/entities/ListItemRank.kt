package com.szoldapps.trakt.entities

data class ListItemRank(
    var rank: List<Long>? = null
) {

    companion object {

        fun from(rank: List<Long>?): ListItemRank? {
            val listItemRank = ListItemRank()
            listItemRank.rank = rank
            return listItemRank
        }
    }

}
