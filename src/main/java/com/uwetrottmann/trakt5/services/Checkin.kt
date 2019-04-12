package com.uwetrottmann.trakt5.services

import com.uwetrottmann.trakt5.entities.EpisodeCheckin
import com.uwetrottmann.trakt5.entities.EpisodeCheckinResponse
import com.uwetrottmann.trakt5.entities.MovieCheckin
import com.uwetrottmann.trakt5.entities.MovieCheckinResponse
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
