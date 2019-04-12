package com.uwetrottmann.trakt5

/**
 * Build website links to trakt entities.
 */
object TraktLink {

    private const val URL_MOVIE = TraktV2.SITE_URL + "/movies/"
    private const val URL_SHOW = TraktV2.SITE_URL + "/shows/"
    private const val URL_SEASON = TraktV2.SITE_URL + "/seasons/"
    private const val URL_EPISODE = TraktV2.SITE_URL + "/episodes/"
    private const val URL_PERSON = TraktV2.SITE_URL + "/people/"
    private const val URL_COMMENT = TraktV2.SITE_URL + "/comments/"
    private const val URL_IMDB = TraktV2.SITE_URL + "/search/imdb/"
    private const val URL_TMDB = TraktV2.SITE_URL + "/search/tmdb/"
    private const val URL_TVDB = TraktV2.SITE_URL + "/search/tvdb/"
    private const val URL_TVRAGE = TraktV2.SITE_URL + "/search/tvrage/"

    private const val PATH_SEASONS = "/seasons/"
    private const val PATH_EPISODES = "/episodes/"

    /**
     * Creates a direct link to this movie.
     */
    fun movie(idOrSlug: String): String = URL_MOVIE + idOrSlug

    /**
     * Creates a direct link to this show.
     */
    fun show(idOrSlug: String): String = URL_SHOW + idOrSlug

    /**
     * Creates a direct link to this season.
     */
    fun season(id: Int): String = URL_SEASON + id

    /**
     * Creates a direct link to this season.
     */
    fun season(showId: Int, season: Int): String = show(showId.toString()) + PATH_SEASONS + season

    /**
     * Creates a direct link to this episode.
     */
    fun episode(id: Int): String = URL_EPISODE + id

    /**
     * Creates a direct link to this episode.
     */
    fun episode(showId: Int, season: Int, episode: Int): String =
        show(showId.toString()) + PATH_SEASONS + season + PATH_EPISODES + episode

    /**
     * Creates a direct link to this person.
     */
    fun person(idOrSlug: String): String = URL_PERSON + idOrSlug

    /**
     * Creates a direct link to this comment.
     */
    fun comment(id: Int): String = URL_COMMENT + id

    /**
     * Creates a link to a show, movie or person search for this id.
     */
    fun imdb(imdbId: String): String = URL_IMDB + imdbId

    /**
     * Creates a link to a show or movie search for this id. Keep in mind that TMDb ids are not unique among shows and
     * movies, so a search result page may be displayed.
     */
    fun tmdb(tmdbId: Int): String = URL_TMDB + tmdbId

    /**
     * Creates a link to a show and episode search for this id.
     */
    fun tvdb(tvdbId: Int): String = URL_TVDB + tvdbId

    /**
     * Creates a link to a show search for this id.
     */
    fun tvrage(tvrageId: Int): String = URL_TVRAGE + tvrageId

}
