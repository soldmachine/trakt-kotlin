package com.szoldapps.trakt.services

import com.szoldapps.trakt.entities.Comment
import com.szoldapps.trakt.entities.Credits
import com.szoldapps.trakt.entities.Movie
import com.szoldapps.trakt.entities.MovieTranslation
import com.szoldapps.trakt.entities.Ratings
import com.szoldapps.trakt.entities.Stats
import com.szoldapps.trakt.entities.TrendingMovie
import com.szoldapps.trakt.enums.Extended
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Movies {

    /**
     * Returns the most popular movies. Popularity is calculated using the rating percentage and the number of ratings.
     *
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("movies/popular")
    fun popular(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Movie>>

    /**
     * Returns all movies being watched right now. Movies with the most users are returned first.
     *
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("movies/trending")
    fun trending(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<TrendingMovie>>

    /**
     * Returns a single movie's details.
     *
     * @param movieId trakt ID, trakt slug, or IMDB ID. Example: "tron-legacy-2010".
     */
    @GET("movies/{id}")
    fun summary(
        @Path("id") movieId: String,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<Movie>

    /**
     * Returns all translations for a movie, including language and translated values for title, tagline and overview.
     *
     * @param movieId trakt ID, trakt slug, or IMDB ID. Example: "tron-legacy-2010".
     */
    @GET("movies/{id}/translations")
    fun translations(
        @Path("id") movieId: String
    ): Call<List<MovieTranslation>>

    /**
     * Returns a single translation for a movie. If the translation does not exist, the returned list will be empty.
     *
     * @param movieId trakt ID, trakt slug, or IMDB ID. Example: "tron-legacy-2010".
     * @param language 2-letter language code (ISO 639-1).
     */
    @GET("movies/{id}/translations/{language}")
    fun translation(
        @Path("id") movieId: String,
        @Path("language") language: String
    ): Call<List<MovieTranslation>>

    /**
     * Returns all top level comments for a movie. Most recent comments returned first.
     *
     * @param movieId trakt ID, trakt slug, or IMDB ID. Example: "tron-legacy-2010".
     * @param page Number of page of results to be returned. If `null` defaults to 1.
     * @param limit Number of results to return per page. If `null` defaults to 10.
     */
    @GET("movies/{id}/comments")
    fun comments(
        @Path("id") movieId: String,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Comment>>

    /**
     * Returns all actors, directors, writers, and producers for a movie.
     *
     * @param movieId trakt ID, trakt slug, or IMDB ID. Example: "tron-legacy-2010".
     */
    @GET("movies/{id}/people")
    fun people(
        @Path("id") movieId: String
    ): Call<Credits>

    /**
     * Returns rating (between 0 and 10) and distribution for a movie.
     *
     * @param movieId trakt ID, trakt slug, or IMDB ID. Example: "tron-legacy-2010".
     */
    @GET("movies/{id}/ratings")
    fun ratings(
        @Path("id") movieId: String
    ): Call<Ratings>

    /**
     * Returns lots of movie stats.
     */
    @GET("movies/{id}/stats")
    fun stats(
        @Path("id") movieId: String
    ): Call<Stats>

}
