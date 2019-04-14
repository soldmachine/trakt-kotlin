package com.szoldapps.trakt.services

import com.szoldapps.trakt.entities.BaseMovie
import com.szoldapps.trakt.entities.BaseShow
import com.szoldapps.trakt.entities.LastActivities
import com.szoldapps.trakt.entities.RatedEpisode
import com.szoldapps.trakt.entities.RatedMovie
import com.szoldapps.trakt.entities.RatedSeason
import com.szoldapps.trakt.entities.RatedShow
import com.szoldapps.trakt.entities.SyncItems
import com.szoldapps.trakt.entities.SyncResponse
import com.szoldapps.trakt.entities.WatchlistedEpisode
import com.szoldapps.trakt.entities.WatchlistedSeason
import com.szoldapps.trakt.enums.Extended
import com.szoldapps.trakt.enums.RatingsFilter
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Sync {

    /**
     * **OAuth Required**
     *
     * This method is a useful first step in the syncing process. We recommended caching these dates locally, then
     * you can compare to know exactly what data has changed recently. This can greatly optimize your syncs so you don't
     * pull down a ton of data only to see nothing has actually changed.
     */
    @GET("sync/last_activities")
    fun lastActivities(): Call<LastActivities>

    /**
     * **OAuth Required**
     *
     * Get all collected movies in a user's collection. A collected item indicates availability to watch digitally
     * or on physical media.
     */
    @GET("sync/collection/movies")
    fun collectionMovies(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<BaseMovie>>

    /**
     * **OAuth Required**
     *
     * Get all collected shows in a user's collection. A collected item indicates availability to watch digitally
     * or
     * on physical media.
     */
    @GET("sync/collection/shows")
    fun collectionShows(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<BaseShow>>

    /**
     * **OAuth Required**
     *
     * Add one or more items to a user's collection including the format of the item.
     *
     * @param items A list of movies, shows, seasons or episodes.
     */
    @POST("sync/collection")
    fun addItemsToCollection(
        @Body items: SyncItems
    ): Call<SyncResponse>

    /**
     * **OAuth Required**
     *
     * Remove one or more items from a user's collection.
     *
     * @param items A list of movies, shows, seasons or episodes.
     */
    @POST("sync/collection/remove")
    fun deleteItemsFromCollection(
        @Body items: SyncItems
    ): Call<SyncResponse>

    /**
     * **OAuth Required**
     *
     * Returns all movies a user has watched.
     */
    @GET("sync/watched/movies")
    fun watchedMovies(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<BaseMovie>>

    /**
     * **OAuth Required**
     *
     * Returns all shows a user has watched.
     */
    @GET("sync/watched/shows")
    fun watchedShows(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<BaseShow>>

    /**
     * **OAuth Required**
     *
     * Add items to a user's watch history. Accepts shows, seasons, episodes and movies. If only a show is passed,
     * assumes all seasons are to be marked watched. Same for seasons. Send a `watched_at` UTC datetime to
     * mark items as watched in the past. This is useful for syncing past watches from a media center.
     *
     * @param items A list of movies, shows, seasons or episodes.
     */
    @POST("sync/history")
    fun addItemsToWatchedHistory(
        @Body items: SyncItems
    ): Call<SyncResponse>

    /**
     * **OAuth Required**
     *
     * Remove items from a user's watch history including all watches, scrobbles, and checkins. Accepts shows,
     * seasons, episodes and movies. If only a show is passed, assumes all seasons are to be removed from history. Same
     * for seasons.
     *
     * @param items A list of movies, shows, seasons or episodes.
     */
    @POST("sync/history/remove")
    fun deleteItemsFromWatchedHistory(
        @Body items: SyncItems
    ): Call<SyncResponse>

    /**
     * **OAuth Required**
     *
     * Get a user's ratings filtered by movies. You can filter for a specific rating between 1 and 10.
     *
     * @param filter Filter for a specific rating.
     */
    @GET("sync/ratings/movies{rating}")
    fun ratingsMovies(
        @Path(value = "rating", encoded = true) filter: RatingsFilter,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<RatedMovie>>

    /**
     * **OAuth Required**
     *
     * Get a user's ratings filtered by shows. You can filter for a specific rating between 1 and 10.
     *
     * @param filter Filter for a specific rating.
     */
    @GET("sync/ratings/shows{rating}")
    fun ratingsShows(
        @Path(value = "rating", encoded = true) filter: RatingsFilter,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<RatedShow>>

    /**
     * **OAuth Required**
     *
     * Get a user's ratings filtered by seasons. You can filter for a specific rating between 1 and 10.
     *
     * @param filter Filter for a specific rating.
     */
    @GET("sync/ratings/seasons{rating}")
    fun ratingsSeasons(
        @Path(value = "rating", encoded = true) filter: RatingsFilter,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<RatedSeason>>

    /**
     * **OAuth Required**
     *
     * Get a user's ratings filtered by episodes. You can filter for a specific rating between 1 and 10.
     *
     * @param filter Filter for a specific rating.
     */
    @GET("sync/ratings/episodes{rating}")
    fun ratingsEpisodes(
        @Path(value = "rating", encoded = true) filter: RatingsFilter,
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<RatedEpisode>>

    /**
     * **OAuth Required**
     *
     * Rate one or more items.
     *
     * @param items A list of movies, shows, seasons or episodes.
     */
    @POST("sync/ratings")
    fun addRatings(
        @Body items: SyncItems
    ): Call<SyncResponse>

    /**
     * **OAuth Required**
     *
     * Delete ratings for one or more items.
     *
     * @param items A list of movies, shows, seasons or episodes.
     */
    @POST("sync/ratings/remove")
    fun deleteRatings(
        @Body items: SyncItems
    ): Call<SyncResponse>


    /**
     * **OAuth Required**
     *
     * Returns all items in a user's watchlist filtered by movies. When an item is watched, it will be
     * automatically
     * removed from the watchlist. To track what the user is actively watching, use the progress APIs.
     */
    @GET("sync/watchlist/movies")
    fun watchlistMovies(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<BaseMovie>>

    /**
     * **OAuth Required**
     *
     * Returns all items in a user's watchlist filtered by shows. When an item is watched, it will be automatically
     * removed from the watchlist. To track what the user is actively watching, use the progress APIs.
     */
    @GET("sync/watchlist/shows")
    fun watchlistShows(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<BaseShow>>

    /**
     * **OAuth Required**
     *
     * Returns all items in a user's watchlist filtered by seasons. When an item is watched, it will be
     * automatically removed from the watchlist. To track what the user is actively watching, use the progress APIs.
     */
    @GET("sync/watchlist/seasons")
    fun watchlistSeasons(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<WatchlistedSeason>>

    /**
     * **OAuth Required**
     *
     * Returns all items in a user's watchlist filtered by episodes. When an item is watched, it will be
     * automatically removed from the watchlist. To track what the user is actively watching, use the progress APIs.
     */
    @GET("sync/watchlist/episodes")
    fun watchlistEpisodes(
        @Query(value = "extended", encoded = true) extended: Extended
    ): Call<List<WatchlistedEpisode>>

    /**
     * **OAuth Required**
     *
     * Add one of more items to a user's watchlist.
     *
     * @param items A list of movies, shows, seasons or episodes.
     */
    @POST("sync/watchlist")
    fun addItemsToWatchlist(
        @Body items: SyncItems
    ): Call<SyncResponse>

    /**
     * **OAuth Required**
     *
     * Delete one or more items from a user's watchlist.
     *
     * @param items A list of movies, shows, seasons or episodes.
     */
    @POST("sync/watchlist/remove")
    fun deleteItemsFromWatchlist(
        @Body items: SyncItems
    ): Call<SyncResponse>

}
