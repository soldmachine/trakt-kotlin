package com.uwetrottmann.trakt5.services

import com.uwetrottmann.trakt5.entities.Credits
import com.uwetrottmann.trakt5.entities.Person
import com.uwetrottmann.trakt5.enums.Extended
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface People {

    /**
     * Returns a single person's details.
     *
     * @param personId trakt ID, trakt slug, or IMDB ID Example: bryan-cranston.
     */
    @GET("people/{id}")
    fun summary(
        @Path("id") personId: String,
        @Query("extended") extended: Extended
    ): Call<Person>

    @GET("people/{id}/movies")
    fun movieCredits(
        @Path("id") personId: String
    ): Call<Credits>

    @GET("people/{id}/shows")
    fun showCredits(
        @Path("id") personId: String
    ): Call<Credits>

}
