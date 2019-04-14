package com.szoldapps.trakt.services

import com.szoldapps.trakt.entities.EpisodeCheckin
import com.szoldapps.trakt.entities.EpisodeCheckinResponse
import com.szoldapps.trakt.entities.MovieCheckin
import com.szoldapps.trakt.entities.MovieCheckinResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface Checkin {

    /**
     * **OAuth Required**
     *
     * Check into an episode. This should be tied to a user action to manually indicate they are watching
     * something.
     * The item will display as watching on the site, then automatically switch to watched status once the duration has
     * elapsed.
     */
    @POST("checkin")
    fun checkin(
        @Body episodeCheckin: EpisodeCheckin
    ): Call<EpisodeCheckinResponse>

    /**
     * **OAuth Required**
     *
     * Check into a movie. This should be tied to a user action to manually indicate they are watching something.
     * The item will display as watching on the site, then automatically switch to watched status once the duration has
     * elapsed.
     */
    @POST("checkin")
    fun checkin(
        @Body movieCheckin: MovieCheckin
    ): Call<MovieCheckinResponse>

    /**
     * **OAuth Required**
     *
     * Removes any active checkins, no need to provide a specific item.
     */
    @DELETE("checkin")
    fun deleteActiveCheckin(): Call<Void>

}
