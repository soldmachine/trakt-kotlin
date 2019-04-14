package com.szoldapps.trakt.services

import com.szoldapps.trakt.entities.SearchResult
import com.szoldapps.trakt.enums.Extended
import com.szoldapps.trakt.enums.IdType
import com.szoldapps.trakt.enums.Type
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Search {

    /**
     * Search all text fields that a media object contains (i.e. title, overview, etc). Results are ordered by the most
     * relevant score.
     *
     * @see [Search - Text
     * Query](http://docs.trakt.apiary.io/.reference/search/text-query/get-text-query-results)
     *
     * @see [Filters](http://docs.trakt.apiary.io/.introduction/filters)
     *
     * @see [Extended](http://docs.trakt.apiary.io/.introduction/extended-info)
     *
     * @see [Pagination](http://docs.trakt.apiary.io/.introduction/pagination)
     */
    @GET("search/{type}")
    fun textQuery(
        @Path("type") type: Type,
        @Query("query") query: String,
        @Query("years") years: String,
        @Query("genres") genres: String,
        @Query("languages") languages: String,
        @Query("countries") countries: String,
        @Query("runtimes") runtimes: String,
        @Query("ratings") ratings: String,
        @Query("extended") extended: Extended,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Call<List<SearchResult>>

    /**
     * @see .textQuery textQuery
     */
    @GET("search/movie")
    fun textQueryMovie(
        @Query("query") query: String,
        @Query("years") years: String,
        @Query("genres") genres: String,
        @Query("languages") languages: String,
        @Query("countries") countries: String,
        @Query("runtimes") runtimes: String,
        @Query("ratings") ratings: String,
        @Query("certifications") certifications: String,
        @Query("extended") extended: Extended,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Call<List<SearchResult>>

    /**
     * @see .textQuery textQuery
     */
    @GET("search/show")
    fun textQueryShow(
        @Query("query") query: String,
        @Query("years") years: String,
        @Query("genres") genres: String,
        @Query("languages") languages: String,
        @Query("countries") countries: String,
        @Query("runtimes") runtimes: String,
        @Query("ratings") ratings: String,
        @Query("certifications") certifications: String,
        @Query("networks") networks: String,
        @Query("status") status: String,
        @Query("extended") extended: Extended,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Call<List<SearchResult>>

    /**
     * Lookup items by their Trakt, IMDB, TMDB, TVDB, or TVRage ID.
     *
     * @see [Search - ID
     * Lookup](http://docs.trakt.apiary.io/.reference/search/id-lookup/get-id-lookup-results)
     *
     * @see [Extended](http://docs.trakt.apiary.io/.introduction/extended-info)
     *
     * @see [Pagination](http://docs.trakt.apiary.io/.introduction/pagination)
     */
    @GET("search/{id_type}/{id}")
    fun idLookup(
        @Path(value = "id_type", encoded = true) idType: IdType,
        @Path(value = "id", encoded = true) id: String,
        @Query("type") type: Type,
        @Query("extended") extended: Extended,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ): Call<List<SearchResult>>

}
