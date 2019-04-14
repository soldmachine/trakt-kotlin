package com.szoldapps.trakt

/**
 * Build website links to trakt entities.
 */
object TraktLink {

    private const val URL_MOVIE = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/movies/"
    private const val URL_SHOW = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/shows/"
    private const val URL_SEASON = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/seasons/"
    private const val URL_EPISODE = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/episodes/"
    private const val URL_PERSON = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/people/"
    private const val URL_COMMENT = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/comments/"
    private const val URL_IMDB = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/search/imdb/"
    private const val URL_TMDB = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/search/tmdb/"
    private const val URL_TVDB = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/search/tvdb/"
    private const val URL_TVRAGE = com.szoldapps.trakt.TraktV2.Companion.SITE_URL + "/search/tvrage/"

    private const val PATH_SEASONS = "/seasons/"
    private const val PATH_EPISODES = "/episodes/"

    /**
     * Creates a direct link to this movie.
     */
    fun movie(idOrSlug: String): String = com.szoldapps.trakt.TraktLink.URL_MOVIE + idOrSlug

    /**
     * Creates a direct link to this show.
     */
    fun show(idOrSlug: String): String = com.szoldapps.trakt.TraktLink.URL_SHOW + idOrSlug

    /**
     * Creates a direct link to this season.
     */
    fun season(id: Int): String = com.szoldapps.trakt.TraktLink.URL_SEASON + id

    /**
     * Creates a direct link to this season.
     */
    fun season(showId: Int, season: Int): String = com.szoldapps.trakt.TraktLink.show(
        showId.toString()
    ) + com.szoldapps.trakt.TraktLink.PATH_SEASONS + season

    /**
     * Creates a direct link to this episode.
     */
    fun episode(id: Int): String = com.szoldapps.trakt.TraktLink.URL_EPISODE + id

    /**
     * Creates a direct link to this episode.
     */
    fun episode(showId: Int, season: Int, episode: Int): String =
        com.szoldapps.trakt.TraktLink.show(
            showId.toString()
        ) + com.szoldapps.trakt.TraktLink.PATH_SEASONS + season + com.szoldapps.trakt.TraktLink.PATH_EPISODES + episode

    /**
     * Creates a direct link to this person.
     */
    fun person(idOrSlug: String): String = com.szoldapps.trakt.TraktLink.URL_PERSON + idOrSlug

    /**
     * Creates a direct link to this comment.
     */
    fun comment(id: Int): String = com.szoldapps.trakt.TraktLink.URL_COMMENT + id

    /**
     * Creates a link to a show, movie or person search for this id.
     */
    fun imdb(imdbId: String): String = com.szoldapps.trakt.TraktLink.URL_IMDB + imdbId

    /**
     * Creates a link to a show or movie search for this id. Keep in mind that TMDb ids are not unique among shows and
     * movies, so a search result page may be displayed.
     */
    fun tmdb(tmdbId: Int): String = com.szoldapps.trakt.TraktLink.URL_TMDB + tmdbId

    /**
     * Creates a link to a show and episode search for this id.
     */
    fun tvdb(tvdbId: Int): String = com.szoldapps.trakt.TraktLink.URL_TVDB + tvdbId

    /**
     * Creates a link to a show search for this id.
     */
    fun tvrage(tvrageId: Int): String = com.szoldapps.trakt.TraktLink.URL_TVRAGE + tvrageId

}
