package com.szoldapps.trakt.services

import com.szoldapps.trakt.entities.Comment
import com.szoldapps.trakt.entities.Episode
import com.szoldapps.trakt.entities.Ratings
import com.szoldapps.trakt.entities.Season
import com.szoldapps.trakt.entities.Stats
import com.szoldapps.trakt.enums.Extended
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Seasons {

    /**
     * Returns all seasons for a show including the number of episodes in each season.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}/seasons")
    fun summary(
        @Path("id") showId: String,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Season>>

    /**
     * Returns all episodes for a specific season of a show.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param season Season number.
     */
    @GET("shows/{id}/seasons/{season}")
    fun season(
        @Path("id") showId: String,
        @Path("season") season: Int,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Episode>>

    /**
     * Returns all top level comments for a season. Most recent comments returned first.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param season Season number.
     */
    @GET("shows/{id}/seasons/{season}/comments")
    fun comments(
        @Path("id") showId: String,
        @Path("season") season: Int
    ): Call<List<Comment>>

    /**
     * Returns rating (between 0 and 10) and distribution for a season.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param season Season number.
     */
    @GET("shows/{id}/seasons/{season}/ratings")
    fun ratings(
        @Path("id") showId: String,
        @Path("season") season: Int
    ): Call<Ratings>

    /**
     * Returns lots of season stats.
     */
    @GET("shows/{id}/seasons/{season}/stats")
    fun stats(
        @Path("id") showId: String,
        @Path("season") season: Int
    ): Call<Stats>

}
