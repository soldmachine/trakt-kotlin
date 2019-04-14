package com.szoldapps.trakt.services

import com.szoldapps.trakt.entities.Movie
import com.szoldapps.trakt.entities.Show
import com.szoldapps.trakt.enums.Extended
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Recommendations {

    /**
     * **OAuth Required**
     *
     *  Personalized movie recommendations for a user. Results returned with the top recommendation first.
     */
    @GET("recommendations/movies")
    fun movies(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Movie>>

    /**
     * **OAuth Required**
     *
     * Dismiss a movie from getting recommended anymore.
     *
     * @param movieId trakt ID, trakt slug, or IMDB ID. Example: "tron-legacy-2010".
     */
    @DELETE("recommendations/movies/{id}")
    fun dismissMovie(
        @Path("id") movieId: String
    ): Call<Void>

    /**
     * **OAuth Required**
     *
     * Personalized show recommendations for a user. Results returned with the top recommendation first.
     */
    @GET("recommendations/shows")
    fun shows(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<Show>>

    /**
     * **OAuth Required**
     *
     * Dismiss a show from getting recommended anymore.
     *
     * @param showId trakt ID, trakt slug, or IMDB ID. Example: 922.
     */
    @DELETE("recommendations/shows/{id}")
    fun dismissShow(
        @Path("id") showId: String
    ): Call<Void>

}
