package com.szoldapps.trakt.entities

import java.util.ArrayList

data class SyncItems(
    var movies: List<SyncMovie>? = null,
    var shows: List<SyncShow>? = null,
    var episodes: List<SyncEpisode>? = null,
    var people: List<SyncPerson>? = null,

    /**
     * Only supported for removing specific history items.
     */
    var ids: List<Long>? = null
) {

    fun movies(movie: SyncMovie): SyncItems? {
        val list = ArrayList<SyncMovie>(1)
        list.add(movie)
        return movies(list)
    }

    fun movies(movies: List<SyncMovie>?): SyncItems? {
        this.movies = movies
        return this
    }

    fun shows(show: SyncShow): SyncItems? {
        val list = ArrayList<SyncShow>(1)
        list.add(show)
        return shows(list)
    }

    fun shows(shows: List<SyncShow>?): SyncItems? {
        this.shows = shows
        return this
    }

    fun episodes(episode: SyncEpisode): SyncItems? {
        val list = ArrayList<SyncEpisode>(1)
        list.add(episode)
        return episodes(list)
    }

    fun episodes(episodes: List<SyncEpisode>?): SyncItems? {
        this.episodes = episodes
        return this
    }

    fun people(person: SyncPerson): SyncItems? {
        val list = ArrayList<SyncPerson>(1)
        list.add(person)
        return people(list)
    }

    fun people(people: List<SyncPerson>?): SyncItems? {
        this.people = people
        return this
    }


    @Deprecated("use {@link #ids(long)} instead")
    fun ids(id: Int): SyncItems? {
        return ids(id.toLong())
    }

    /**
     * History id to be removed.
     */
    fun ids(id: Long): SyncItems? {
        val list = ArrayList<Long>(1)
        list.add(id)
        return ids(list)
    }

    /**
     * History ids to be removed.
     */
    fun ids(ids: List<Long>?): SyncItems? {
        this.ids = ids
        return this
    }
}
