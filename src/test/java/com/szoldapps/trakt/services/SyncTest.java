package com.szoldapps.trakt.services;

import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.TestData;
import com.szoldapps.trakt.entities.BaseMovie;
import com.szoldapps.trakt.entities.BaseShow;
import com.szoldapps.trakt.entities.LastActivities;
import com.szoldapps.trakt.entities.LastActivity;
import com.szoldapps.trakt.entities.LastActivityMore;
import com.szoldapps.trakt.entities.ListsLastActivity;
import com.szoldapps.trakt.entities.MovieIds;
import com.szoldapps.trakt.entities.RatedEpisode;
import com.szoldapps.trakt.entities.RatedMovie;
import com.szoldapps.trakt.entities.RatedSeason;
import com.szoldapps.trakt.entities.RatedShow;
import com.szoldapps.trakt.entities.ShowIds;
import com.szoldapps.trakt.entities.SyncItems;
import com.szoldapps.trakt.entities.SyncMovie;
import com.szoldapps.trakt.entities.SyncResponse;
import com.szoldapps.trakt.entities.SyncSeason;
import com.szoldapps.trakt.entities.SyncShow;
import com.szoldapps.trakt.entities.WatchlistedEpisode;
import com.szoldapps.trakt.entities.WatchlistedSeason;
import com.szoldapps.trakt.enums.Rating;
import com.szoldapps.trakt.enums.RatingsFilter;
import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.TestData;
import com.szoldapps.trakt.entities.BaseMovie;
import com.szoldapps.trakt.entities.BaseShow;
import com.szoldapps.trakt.entities.LastActivities;
import com.szoldapps.trakt.entities.LastActivity;
import com.szoldapps.trakt.entities.LastActivityMore;
import com.szoldapps.trakt.entities.ListsLastActivity;
import com.szoldapps.trakt.entities.MovieIds;
import com.szoldapps.trakt.entities.RatedEpisode;
import com.szoldapps.trakt.entities.RatedMovie;
import com.szoldapps.trakt.entities.RatedSeason;
import com.szoldapps.trakt.entities.RatedShow;
import com.szoldapps.trakt.entities.ShowIds;
import com.szoldapps.trakt.entities.SyncEpisode;
import com.szoldapps.trakt.entities.SyncItems;
import com.szoldapps.trakt.entities.SyncMovie;
import com.szoldapps.trakt.entities.SyncResponse;
import com.szoldapps.trakt.entities.SyncSeason;
import com.szoldapps.trakt.entities.SyncShow;
import com.szoldapps.trakt.entities.WatchlistedEpisode;
import com.szoldapps.trakt.entities.WatchlistedSeason;
import com.szoldapps.trakt.enums.Rating;
import com.szoldapps.trakt.enums.RatingsFilter;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SyncTest extends BaseTestCase {

    @Test
    public void test_lastActivites() throws IOException {
        LastActivities lastActivities = executeCall(getTrakt().sync().lastActivities());
        assertThat(lastActivities).isNotNull();
        assertThat(lastActivities.getAll()).isNotNull();
        assertLastActivityMore(lastActivities.getMovies());
        assertLastActivityMore(lastActivities.getEpisodes());
        assertLastActivity(lastActivities.getShows());
        assertLastActivity(lastActivities.getSeasons());
        assertListsLastActivity(lastActivities.getLists());
    }

    private void assertLastActivityMore(LastActivityMore activityMore) {
        assertLastActivity(activityMore);
        assertThat(activityMore.getPaused_at()).isNotNull();
        assertThat(activityMore.getCollected_at()).isNotNull();
        assertThat(activityMore.getWatched_at()).isNotNull();
    }

    private void assertLastActivity(LastActivity activity) {
        assertThat(activity).isNotNull();
        assertThat(activity.getCommented_at()).isNotNull();
        assertThat(activity.getRated_at()).isNotNull();
        assertThat(activity.getWatchlisted_at()).isNotNull();
    }

    private void assertListsLastActivity(ListsLastActivity activity) {
        assertThat(activity).isNotNull();
        assertThat(activity.getCommented_at()).isNotNull();
        assertThat(activity.getLiked_at()).isNotNull();
        assertThat(activity.getUpdated_at()).isNotNull();
    }

    @Test
    public void test_collectionMovies() throws IOException {
        List<BaseMovie> movies = executeCall(getTrakt().sync().collectionMovies(null));
        assertSyncMovies(movies, "collection");
    }

    @Test
    public void test_collectionShows() throws IOException {
        List<BaseShow> shows = executeCall(getTrakt().sync().collectionShows(null));
        assertSyncShows(shows, "collection");
    }

    @Test
    public void test_addItemsToCollection_movie() throws IOException {
        SyncMovie movie = new SyncMovie();
        movie.setIds(buildMovieIds());

        SyncItems items = new SyncItems().movies(movie);
        addItemsToCollection(items);
    }

    @Test
    public void test_addItemsToCollection_show() throws IOException {
        SyncShow show = new SyncShow();
        show.setIds(buildShowIds());

        SyncItems items = new SyncItems().shows(show);
        addItemsToCollection(items);
    }

    @Test
    public void test_addItemsToCollection_season() throws IOException {
        // season
        SyncSeason season = new SyncSeason();
        season.setNumber(1);

        // show
        SyncShow show = new SyncShow();
        show.setIds(ShowIds.Companion.slug("community"));
        show.setSeasons(new ArrayList<>());
        show.getSeasons().add(season);

        SyncItems items = new SyncItems().shows(show);
        addItemsToCollection(items);
    }

    @Test
    public void test_addItemsToCollection_episode() throws IOException {
        // Fri Jul 14 2017 02:40:00 UTC
        OffsetDateTime collectedAt = OffsetDateTime.ofInstant(Instant.ofEpochMilli(1500000000000L), ZoneOffset.UTC);

        // episodes
        SyncEpisode episode1 = new SyncEpisode();
        episode1.setNumber(1);
        episode1.collectedAt(collectedAt);
        SyncEpisode episode2 = new SyncEpisode();
        episode2.setNumber(2);

        // season
        SyncSeason season = new SyncSeason();
        season.setNumber(TestData.EPISODE_SEASON);
        season.setEpisodes(new ArrayList<>());
        season.getEpisodes().add(episode1);
        season.getEpisodes().add(episode2);

        // show
        SyncShow show = new SyncShow();
        show.setIds(ShowIds.Companion.tvdb(TestData.SHOW_TVDB_ID));
        show.setSeasons(new ArrayList<>());
        show.getSeasons().add(season);

        SyncItems items = new SyncItems().shows(show);
        addItemsToCollection(items);
    }

    private void addItemsToCollection(SyncItems items) throws IOException {
        SyncResponse response = executeCall(getTrakt().sync().addItemsToCollection(items));
        assertSyncResponse(response);
    }

    private void assertSyncResponse(SyncResponse response) {
        assertThat(response.getAdded().getMovies()).isNotNull();
        assertThat(response.getAdded().getEpisodes()).isNotNull();
        assertThat(response.getExisting().getMovies()).isNotNull();
        assertThat(response.getExisting().getEpisodes()).isNotNull();
        assertThat(response.getNot_found()).isNotNull();
        assertThat(response.getDeleted()).isNull();
    }

    @Test
    public void test_deleteItemsFromCollection() throws IOException {
        SyncResponse response = executeCall(getTrakt().sync().deleteItemsFromCollection(buildItemsForDeletion()));
        assertSyncResponseDelete(response);
    }

    private void assertSyncResponseDelete(SyncResponse response) {
        assertThat(response.getDeleted().getMovies()).isNotNull();
        assertThat(response.getDeleted().getEpisodes()).isNotNull();
        assertThat(response.getExisting()).isNull();
        assertThat(response.getNot_found()).isNotNull();
        assertThat(response.getAdded()).isNull();
    }

    private SyncItems buildItemsForDeletion() {
        // movie
        SyncMovie movie = new SyncMovie();
        movie.setIds(buildMovieIds());

        // episode
        SyncEpisode episode2 = new SyncEpisode();
        episode2.setNumber(2);

        SyncSeason season = new SyncSeason();
        season.setNumber(TestData.EPISODE_SEASON);
        season.setEpisodes(new ArrayList<>());
        season.getEpisodes().add(episode2);

        SyncShow show = new SyncShow();
        show.setIds(ShowIds.Companion.tvdb(TestData.SHOW_TVDB_ID));
        show.setSeasons(new ArrayList<>());
        show.getSeasons().add(season);

        return new SyncItems().movies(movie).shows(show);
    }

    @Test
    public void test_watchedMovies() throws IOException {
        List<BaseMovie> watchedMovies = executeCall(getTrakt().sync().watchedMovies(null));
        assertSyncMovies(watchedMovies, "watched");
    }

    @Test
    public void test_watchedShows() throws IOException {
        List<BaseShow> watchedShows = executeCall(getTrakt().sync().watchedShows(null));
        assertSyncShows(watchedShows, "watched");
    }

    @Test
    public void test_addItemsToWatchedHistory() throws IOException {
        // movie
        SyncMovie movie = new SyncMovie();
        movie.setWatched_at(OffsetDateTime.now().minusHours(1));
        movie.setIds(buildMovieIds());

        // episode
        SyncEpisode episode = new SyncEpisode();
        episode.setNumber(TestData.EPISODE_NUMBER);
        episode.setWatched_at(OffsetDateTime.now().minusHours(1));
        SyncEpisode episode2 = new SyncEpisode();
        episode2.setNumber(2);
        episode2.setWatched_at(OffsetDateTime.now().minusMinutes(30));
        // season
        SyncSeason season = new SyncSeason();
        season.setNumber(TestData.EPISODE_SEASON);
        season.setEpisodes(new ArrayList<>());
        season.getEpisodes().add(episode);
        season.getEpisodes().add(episode2);
        // show
        SyncShow show = new SyncShow();
        show.setIds(ShowIds.Companion.tvdb(TestData.SHOW_TVDB_ID));
        show.setSeasons(new ArrayList<>());
        show.getSeasons().add(season);

        SyncItems items = new SyncItems().movies(movie).shows(show);

        SyncResponse response = executeCall(getTrakt().sync().addItemsToWatchedHistory(items));
        assertThat(response).isNotNull();
        assertThat(response.getAdded().getMovies()).isNotNull();
        assertThat(response.getAdded().getEpisodes()).isNotNull();
        assertThat(response.getExisting()).isNull();
        assertThat(response.getDeleted()).isNull();
        assertThat(response.getNot_found()).isNotNull();
    }

    @Test
    public void test_deleteItemsFromWatchedHistory() throws IOException {
        SyncItems items = buildItemsForDeletion();

        SyncResponse response = executeCall(getTrakt().sync().deleteItemsFromWatchedHistory(items));
        assertThat(response).isNotNull();
        assertThat(response.getDeleted().getMovies()).isNotNull();
        assertThat(response.getDeleted().getEpisodes()).isNotNull();
        assertThat(response.getAdded()).isNull();
        assertThat(response.getExisting()).isNull();
        assertThat(response.getNot_found()).isNotNull();
    }

    @Test
    public void test_ratingsMovies() throws IOException {
        List<RatedMovie> ratedMovies = executeCall(getTrakt().sync().ratingsMovies(RatingsFilter.ALL,
                null));
        assertRatedEntities(ratedMovies);
    }

    @Test
    public void test_ratingsMovies_filtered() throws IOException {
        List<RatedMovie> ratedMovies = executeCall(getTrakt().sync().ratingsMovies(RatingsFilter.TOTALLYNINJA,
                null));
        assertThat(ratedMovies).isNotNull();
        for (RatedMovie movie : ratedMovies) {
            assertThat(movie.getRated_at()).isNotNull();
            Assertions.assertThat(movie.getRating()).isEqualTo(Rating.TOTALLYNINJA);
        }
    }

    @Test
    public void test_ratingsShows() throws IOException {
        List<RatedShow> ratedShows = executeCall(getTrakt().sync().ratingsShows(RatingsFilter.ALL,
                null));
        assertRatedEntities(ratedShows);
    }

    @Test
    public void test_ratingsSeasons() throws IOException {
        List<RatedSeason> ratedSeasons = executeCall(getTrakt().sync().ratingsSeasons(RatingsFilter.ALL,
                null));
        assertRatedEntities(ratedSeasons);
    }

    @Test
    public void test_ratingsEpisodes() throws IOException {
        List<RatedEpisode> ratedEpisodes = executeCall(getTrakt().sync().ratingsEpisodes(RatingsFilter.ALL,
                null));
        assertRatedEntities(ratedEpisodes);
    }

    @Test
    public void test_addRatings_movie() throws IOException {
        SyncMovie movie = new SyncMovie()
                .id(MovieIds.Companion.slug(TestData.MOVIE_SLUG))
                .rating(Rating.MEH);

        SyncItems items = new SyncItems().movies(movie);
        executeCall(getTrakt().sync().addRatings(items));
    }

    @Test
    public void test_addRatings_show() throws IOException {
        SyncShow show = new SyncShow()
                .id(ShowIds.Companion.slug(TestData.SHOW_SLUG))
                .rating(Rating.TERRIBLE);

        SyncItems items = new SyncItems().shows(show);
        executeCall(getTrakt().sync().addRatings(items));
    }

    @Test
    public void test_addRatings_season() throws IOException {
        SyncSeason season = new SyncSeason()
                .number(TestData.EPISODE_SEASON)
                .rating(Rating.FAIR);

        SyncShow show = new SyncShow()
                .id(ShowIds.Companion.slug("community"))
                .seasons(season);

        SyncItems items = new SyncItems().shows(show);
        executeCall(getTrakt().sync().addRatings(items));
    }

    @Test
    public void test_addRatings_episode() throws IOException {
        SyncEpisode episode1 = new SyncEpisode()
                .number(1)
                .rating(Rating.TOTALLYNINJA);
        SyncEpisode episode2 = new SyncEpisode()
                .number(2)
                .rating(Rating.GREAT);

        ArrayList<SyncEpisode> episodes = new ArrayList<>();
        episodes.add(episode1);
        episodes.add(episode2);

        SyncSeason season = new SyncSeason()
                .number(TestData.EPISODE_SEASON)
                .episodes(episodes);

        SyncShow show = new SyncShow()
                .id(ShowIds.Companion.slug(TestData.SHOW_SLUG))
                .seasons(season);

        SyncItems items = new SyncItems().shows(show);
        executeCall(getTrakt().sync().addRatings(items));
    }

    @Test
    public void test_deleteRatings() throws IOException {
        SyncItems items = buildItemsForDeletion();

        SyncResponse response = executeCall(getTrakt().sync().deleteRatings(items));
        assertThat(response).isNotNull();
        assertThat(response.getDeleted().getMovies()).isNotNull();
        assertThat(response.getDeleted().getShows()).isNotNull();
        assertThat(response.getDeleted().getSeasons()).isNotNull();
        assertThat(response.getDeleted().getEpisodes()).isNotNull();
        assertThat(response.getAdded()).isNull();
        assertThat(response.getExisting()).isNull();
        assertThat(response.getNot_found()).isNotNull();
    }

    @Test
    public void test_watchlistMovies() throws IOException {
        List<BaseMovie> movies = executeCall(getTrakt().sync().watchlistMovies(null));
        assertSyncMovies(movies, "watchlist");
    }

    @Test
    public void test_watchlistShows() throws IOException {
        List<BaseShow> shows = executeCall(getTrakt().sync().watchlistShows(null));
        assertThat(shows).isNotNull();
        for (BaseShow show : shows) {
            assertThat(show.getShow()).isNotNull();
            assertThat(show.getListed_at()).isNotNull();
        }
    }

    @Test
    public void test_watchlistSeasons() throws IOException {
        List<WatchlistedSeason> seasons = executeCall(getTrakt().sync().watchlistSeasons(null));
        assertThat(seasons).isNotNull();
        for (WatchlistedSeason season : seasons) {
            assertThat(season.getSeason()).isNotNull();
            assertThat(season.getShow()).isNotNull();
            assertThat(season.getListed_at()).isNotNull();
        }
    }

    @Test
    public void test_watchlistEpisodes() throws IOException {
        List<WatchlistedEpisode> episodes = executeCall(getTrakt().sync().watchlistEpisodes(null));
        assertThat(episodes).isNotNull();
        for (WatchlistedEpisode episode : episodes) {
            assertThat(episode.getEpisode()).isNotNull();
            assertThat(episode.getShow()).isNotNull();
            assertThat(episode.getListed_at()).isNotNull();
        }
    }

    @Test
    public void test_addItemsToWatchlist_movie() throws IOException {
        SyncMovie movie = new SyncMovie();
        movie.setIds(buildMovieIds());

        SyncItems items = new SyncItems().movies(movie);
        addItemsToWatchlist(items);
    }

    @Test
    public void test_addItemsToWatchlist_show() throws IOException {
        SyncShow show = new SyncShow();
        show.setIds(buildShowIds());

        SyncItems items = new SyncItems().shows(show);
        addItemsToWatchlist(items);
    }

    @Test
    public void test_addItemsToWatchlist_season() throws IOException {
        // season
        SyncSeason season = new SyncSeason();
        season.setNumber(1);

        // show
        SyncShow show = new SyncShow();
        show.setIds(ShowIds.Companion.slug("community"));
        show.setSeasons(new ArrayList<>());
        show.getSeasons().add(season);

        SyncItems items = new SyncItems().shows(show);
        addItemsToWatchlist(items);
    }

    @Test
    public void test_addItemsToWatchlist_episodes() throws IOException {
        // episode
        SyncEpisode episode1 = new SyncEpisode();
        episode1.setNumber(1);
        SyncEpisode episode2 = new SyncEpisode();
        episode2.setNumber(2);
        // season
        SyncSeason season = new SyncSeason();
        season.setNumber(TestData.EPISODE_SEASON);
        season.setEpisodes(new ArrayList<>());
        season.getEpisodes().add(episode1);
        season.getEpisodes().add(episode2);
        // show
        SyncShow show = new SyncShow();
        show.setIds(ShowIds.Companion.tvdb(TestData.SHOW_TVDB_ID));
        show.setSeasons(new ArrayList<>());
        show.getSeasons().add(season);

        SyncItems items = new SyncItems().shows(show);
        addItemsToWatchlist(items);
    }

    private void addItemsToWatchlist(SyncItems items) throws IOException {
        SyncResponse requestResponse = executeCall(getTrakt().sync().addItemsToWatchlist(items));
        assertSyncResponse(requestResponse);
    }

    @Test
    public void test_deleteItemsFromWatchlist() throws IOException {
        SyncResponse requestResponse = executeCall(getTrakt().sync().deleteItemsFromWatchlist(
                buildItemsForDeletion()));
        assertSyncResponseDelete(requestResponse);
    }


    private MovieIds buildMovieIds() {
        return MovieIds.Companion.tmdb(TestData.MOVIE_TMDB_ID);
    }

    private ShowIds buildShowIds() {
        return ShowIds.Companion.slug("the-walking-dead");
    }

}
