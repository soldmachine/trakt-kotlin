package com.uwetrottmann.trakt5.services

import com.uwetrottmann.trakt5.entities.BaseShow
import com.uwetrottmann.trakt5.entities.Comment
import com.uwetrottmann.trakt5.entities.Credits
import com.uwetrottmann.trakt5.entities.Ratings
import com.uwetrottmann.trakt5.entities.Show
import com.uwetrottmann.trakt5.entities.Stats
import com.uwetrottmann.trakt5.entities.Translation
import com.uwetrottmann.trakt5.entities.TrendingShow
import com.uwetrottmann.trakt5.enums.Extended
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Shows {

    /**
     * Returns the most popular shows. Popularity is calculated using the rating percentage and the number of ratings.
     *
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("shows/popular")
    fun popular(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Show>>

    /**
     * Returns all shows being watched right now. Shows with the most users are returned first.
     *
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("shows/trending")
    fun trending(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<TrendingShow>>

    /**
     * Returns a single shows's details.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}")
    fun summary(
        @Path("id") showId: String,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<Show>

    /**
     * Returns all translations for a show, including language and translated values for title and overview.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}/translations")
    fun translations(
        @Path("id") showId: String
    ): Call<List<Translation>>

    /**
     * Returns a single translation for a show. If the translation does not exist, the returned list will be empty.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param language 2-letter language code (ISO 639-1).
     */
    @GET("shows/{id}/translations/{language}")
    fun translation(
        @Path("id") showId: String,
        @Path("language") language: String
    ): Call<List<Translation>>

    /**
     * Returns all top level comments for a show. Most recent comments returned first.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("shows/{id}/comments")
    fun comments(
        @Path("id") showId: String,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Comment>>

    /**
     * **OAuth Required**
     *
     * Returns collection progress for show including details on all seasons and episodes. The `next_episode`
     * will be the next episode the user should collect, if there are no upcoming episodes it will be set to `null`.
     *
     *
     * By default, any hidden seasons will be removed from the response and stats. To include these and adjust the
     * completion stats, set the `hidden` flag to `true`.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param hidden Include any hidden seasons.
     * @param specials Include specials as season 0.
     */
    @GET("shows/{id}/progress/collection")
    fun collectedProgress(
        @Path("id") showId: String,
        @Query("hidden") hidden: Boolean?,
        @Query("specials") specials: Boolean?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<BaseShow>

    /**
     * **OAuth Required**
     *
     * Returns watched progress for show including details on all seasons and episodes. The `next_episode` will be
     * the next episode the user should watch, if there are no upcoming episodes it will be set to `null`.
     *
     *
     * By default, any hidden seasons will be removed from the response and stats. To include these and adjust the
     * completion stats, set the `hidden` flag to `true`.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     * @param hidden Include any hidden seasons.
     * @param specials Include specials as season 0.
     */
    @GET("shows/{id}/progress/watched")
    fun watchedProgress(
        @Path("id") showId: String,
        @Query("hidden") hidden: Boolean?,
        @Query("specials") specials: Boolean?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<BaseShow>

    /**
     * Returns all actors, directors, writers, and producers for a show.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}/people")
    fun people(
        @Path("id") showId: String
    ): Call<Credits>

    /**
     * Returns rating (between 0 and 10) and distribution for a show.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: "game-of-thrones".
     */
    @GET("shows/{id}/ratings")
    fun ratings(
        @Path("id") showId: String
    ): Call<Ratings>

    /**
     * Returns lots of show stats.
     */
    @GET("shows/{id}/stats")
    fun stats(
        @Path("id") showId: String
    ): Call<Stats>

    /**
     * Returns related and similar shows.
     */
    @GET("shows/{id}/related")
    fun related(
        @Path("id") showId: String,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Show>>

}
