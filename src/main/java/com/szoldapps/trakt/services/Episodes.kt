package com.szoldapps.trakt.services

import com.szoldapps.trakt.entities.Comment
import com.szoldapps.trakt.entities.Episode
import com.szoldapps.trakt.entities.Ratings
import com.szoldapps.trakt.entities.Stats
import com.szoldapps.trakt.enums.Extended
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Episodes {

    /**
     * Returns a single episode's details.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param season Season number.
     * @param episode Episode number.
     */
    @GET("shows/{id}/seasons/{season}/episodes/{episode}")
    fun summary(
        @Path("id") showId: String,
        @Path("season") season: Int,
        @Path("episode") episode: Int,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<Episode>

    /**
     * Returns all top level comments for an episode. Most recent comments returned first.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param season Season number.
     * @param episode Episode number.
     */
    @GET("shows/{id}/seasons/{season}/episodes/{episode}/comments")
    fun comments(
        @Path("id") showId: String,
        @Path("season") season: Int,
        @Path("episode") episode: Int,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Comment>>

    /**
     * Returns rating (between 0 and 10) and distribution for an episode.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param season Season number.
     * @param episode Episode number.
     */
    @GET("shows/{id}/seasons/{season}/episodes/{episode}/ratings")
    fun ratings(
        @Path("id") showId: String,
        @Path("season") season: Int,
        @Path("episode") episode: Int
    ): Call<Ratings>

    /**
     * Returns lots of episode stats.
     */
    @GET("shows/{id}/seasons/{season}/episodes/{episode}/stats")
    fun stats(
        @Path("id") showId: String,
        @Path("season") season: Int,
        @Path("episode") episode: Int
    ): Call<Stats>

}
