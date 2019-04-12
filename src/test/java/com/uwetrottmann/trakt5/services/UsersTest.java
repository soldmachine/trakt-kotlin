package com.uwetrottmann.trakt5.services;

import com.uwetrottmann.trakt5.BaseTestCase;
import com.uwetrottmann.trakt5.TestData;
import com.uwetrottmann.trakt5.entities.BaseMovie;
import com.uwetrottmann.trakt5.entities.BaseShow;
import com.uwetrottmann.trakt5.entities.Followed;
import com.uwetrottmann.trakt5.entities.Follower;
import com.uwetrottmann.trakt5.entities.Friend;
import com.uwetrottmann.trakt5.entities.HistoryEntry;
import com.uwetrottmann.trakt5.entities.ListEntry;
import com.uwetrottmann.trakt5.entities.ListItemRank;
import com.uwetrottmann.trakt5.entities.ListReorderResponse;
import com.uwetrottmann.trakt5.entities.MovieIds;
import com.uwetrottmann.trakt5.entities.PersonIds;
import com.uwetrottmann.trakt5.entities.RatedEpisode;
import com.uwetrottmann.trakt5.entities.RatedMovie;
import com.uwetrottmann.trakt5.entities.RatedSeason;
import com.uwetrottmann.trakt5.entities.RatedShow;
import com.uwetrottmann.trakt5.entities.Settings;
import com.uwetrottmann.trakt5.entities.ShowIds;
import com.uwetrottmann.trakt5.entities.SyncItems;
import com.uwetrottmann.trakt5.entities.SyncMovie;
import com.uwetrottmann.trakt5.entities.SyncPerson;
import com.uwetrottmann.trakt5.entities.SyncResponse;
import com.uwetrottmann.trakt5.entities.SyncShow;
import com.uwetrottmann.trakt5.entities.TraktList;
import com.uwetrottmann.trakt5.entities.User;
import com.uwetrottmann.trakt5.entities.UserSlug;
import com.uwetrottmann.trakt5.entities.WatchlistedEpisode;
import com.uwetrottmann.trakt5.entities.WatchlistedSeason;
import com.uwetrottmann.trakt5.enums.Extended;
import com.uwetrottmann.trakt5.enums.HistoryType;
import com.uwetrottmann.trakt5.enums.ListPrivacy;
import com.uwetrottmann.trakt5.enums.Rating;
import com.uwetrottmann.trakt5.enums.RatingsFilter;
import org.junit.Test;
import org.threeten.bp.LocalTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest extends BaseTestCase {

    private static final int TEST_LIST_WITH_ITEMS_TRAKT_ID = 1012786;

    @Test
    public void test_getSettings() throws IOException {
        Settings settings = executeCall(getTrakt().users().settings());
        assertThat(settings.getUser()).isNotNull();
        assertThat(settings.getAccount()).isNotNull();
        assertThat(settings.getConnections()).isNotNull();
        assertThat(settings.getSharing_text()).isNotNull();
    }

    @Test
    public void test_profile() throws IOException {
        User user = executeCall(getTrakt().users().profile(TestData.USER_SLUG, Extended.FULL));
        assertThat(user.getUsername()).isEqualTo(TestData.USERNAME_STRING);
        assertThat(user.isPrivate()).isEqualTo(false);
        assertThat(user.getName()).isEqualTo(TestData.USER_REAL_NAME);
        assertThat(user.getVip()).isEqualTo(true);
        assertThat(user.getVip_ep()).isEqualTo(true);
        assertThat(user.getIds().getSlug()).isEqualTo(TestData.USERNAME_STRING);
        assertThat(user.getImages().getAvatar().getFull()).isNotEmpty();
    }

    @Test
    public void test_collectionMovies() throws IOException {
        List<BaseMovie> movies = executeCall(
                getTrakt().users().collectionMovies(TestData.USER_SLUG, null));
        assertSyncMovies(movies, "collection");
    }

    @Test
    public void test_collectionShows() throws IOException {
        List<BaseShow> shows = executeCall(getTrakt().users().collectionShows(TestData.USER_SLUG, null));
        assertSyncShows(shows, "collection");
    }

    @Test
    public void test_lists() throws IOException {
        List<TraktList> lists = executeCall(getTrakt().users().lists(UserSlug.Companion.getME()));
        for (TraktList list : lists) {
            // ensure id and a title
            assertThat(list.getIds()).isNotNull();
            assertThat(list.getIds().getTrakt()).isNotNull();
            assertThat(list.getName()).isNotEmpty();
            assertThat(list.getDescription()).isNotEmpty();
            assertThat(list.getPrivacy()).isNotNull();
            assertThat(list.getDisplay_numbers()).isNotNull();
            assertThat(list.getAllow_comments()).isNotNull();
            assertThat(list.getSort_by()).isNotNull();
            assertThat(list.getSort_how()).isNotNull();
            assertThat(list.getCreated_at()).isNotNull();
            assertThat(list.getUpdated_at()).isNotNull();
            assertThat(list.getItem_count()).isPositive();
            assertThat(list.getComment_count()).isGreaterThanOrEqualTo(0);
            assertThat(list.getLikes()).isGreaterThanOrEqualTo(0);
            assertThat(list.getUser()).isNotNull();
        }
    }

    @Test
    public void test_createList() throws IOException {
        TraktList list = new TraktList();
        list.name("trakt-java");
        list.description("trakt-java test list");
        list.privacy(ListPrivacy.PUBLIC);
        list.allowComments(false);
        list.displayNumbers(false);

        // create list...
        TraktList createdList = executeCall(getTrakt().users().createList(UserSlug.Companion.getME(), list));
        assertThat(createdList.getIds().getTrakt()).isNotNull();
        assertThat(createdList.getName()).isEqualTo(list.getName());
        assertThat(createdList.getDescription()).isEqualTo(list.getDescription());

        // ...and delete it again
        Response deleteResponse = getTrakt().users().deleteList(UserSlug.Companion.getME(),
                String.valueOf(createdList.getIds().getTrakt())).execute();
        assertSuccessfulResponse(deleteResponse);
        assertThat(deleteResponse.code()).isEqualTo(204);
    }


    @Test
    public void test_updateList() throws IOException {
        // change name (append a new suffix that changes frequently)
        int secondOfDay = LocalTime.now().toSecondOfDay();
        TraktList list = new TraktList();
        list.name("trakt-java " + secondOfDay);

        // create list...
        TraktList updatedList = executeCall(getTrakt().users().updateList(UserSlug.Companion.getME(), String.valueOf(
                TEST_LIST_WITH_ITEMS_TRAKT_ID), list));
        assertThat(updatedList.getIds().getTrakt()).isEqualTo(TEST_LIST_WITH_ITEMS_TRAKT_ID);
        assertThat(updatedList.getName()).isEqualTo(list.getName());
    }

    @Test
    public void test_listItems() throws IOException {
        List<ListEntry> entries = executeCall(getTrakt().users().listItems(UserSlug.Companion.getME(),
                String.valueOf(TEST_LIST_WITH_ITEMS_TRAKT_ID),
                null));
        for (ListEntry entry : entries) {
            assertThat(entry.getListed_at()).isNotNull();
            assertThat(entry.getId()).isNotNull();
            assertThat(entry.getRank()).isNotNull();
            assertThat(entry.getType()).isNotNull();
        }
    }

    @Test
    public void test_addListItems() throws IOException {
        SyncShow show = new SyncShow().id(ShowIds.Companion.tvdb(256227));
        SyncMovie movie = new SyncMovie().id(MovieIds.Companion.tmdb(TestData.MOVIE_TMDB_ID));
        SyncPerson person = new SyncPerson().id(PersonIds.Companion.tmdb(TestData.PERSON_TMDB_ID));

        SyncItems items = new SyncItems();
        items.shows(show);
        items.movies(movie);
        items.people(person);

        // add items...
        SyncResponse response = executeCall(getTrakt().users().addListItems(UserSlug.Companion.getME(),
                String.valueOf(TEST_LIST_WITH_ITEMS_TRAKT_ID),
                items));

        assertThat(response.getAdded().getShows()).isEqualTo(1);
        assertThat(response.getAdded().getMovies()).isEqualTo(1);
        assertThat(response.getAdded().getPeople()).isEqualTo(1);

        // ...and remove them again
        response = executeCall(
                getTrakt().users().deleteListItems(UserSlug.Companion.getME(), String.valueOf(TEST_LIST_WITH_ITEMS_TRAKT_ID),
                        items));

        assertThat(response.getDeleted().getShows()).isEqualTo(1);
        assertThat(response.getDeleted().getMovies()).isEqualTo(1);
        assertThat(response.getDeleted().getPeople()).isEqualTo(1);
    }

    @Test
    public void test_reorderListItems() throws IOException {
        List<ListEntry> entries = executeCall(getTrakt().users().listItems(UserSlug.Companion.getME(),
                String.valueOf(TEST_LIST_WITH_ITEMS_TRAKT_ID),
                null));

        // reverse order
        List<Long> newRank = new ArrayList<>();
        for (int i = entries.size() - 1; i >= 0; i--) {
            newRank.add(entries.get(i).getId());
        }

        ListReorderResponse response = executeCall(getTrakt().users().reorderListItems(
                UserSlug.Companion.getME(),
                String.valueOf(TEST_LIST_WITH_ITEMS_TRAKT_ID),
                ListItemRank.Companion.from(newRank)
        ));
        assertThat(response.getUpdated()).isEqualTo(entries.size());
    }

    @Test
    public void test_unfollowAndFollow() throws InterruptedException, IOException {
        // unfollow first
        UserSlug userToFollow = new UserSlug(TestData.USER_TO_FOLLOW);
        Response response = getTrakt().users().unfollow(userToFollow).execute();
        assertSuccessfulResponse(response);
        assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_NO_CONTENT);

        // give the server some time to handle the data
        Thread.sleep(1000);

        // follow again
        Followed followedResponse = executeCall(getTrakt().users().follow(userToFollow));
        assertThat(followedResponse.getUser().getUsername()).isEqualTo(TestData.USER_TO_FOLLOW);
    }

    @Test
    public void test_followers() throws IOException {
        List<Follower> followers = executeCall(getTrakt().users().followers(TestData.USER_SLUG, null));
        for (Follower follower : followers) {
            assertThat(follower.getFollowed_at()).isNotNull();
            assertThat(follower.getUser()).isNotNull();
        }
    }

    @Test
    public void test_following() throws IOException {
        List<Follower> following = executeCall(getTrakt().users().following(TestData.USER_SLUG, null));
        for (Follower follower : following) {
            assertThat(follower.getFollowed_at()).isNotNull();
            assertThat(follower.getUser()).isNotNull();
        }
    }

    @Test
    public void test_friends() throws IOException {
        List<Friend> friends = executeCall(getTrakt().users().friends(TestData.USER_SLUG, null));
        for (Friend friend : friends) {
            assertThat(friend.getFriends_at()).isNotNull();
            assertThat(friend.getUser()).isNotNull();
        }
    }

    @Test
    public void test_historyEpisodesAndMovies() throws IOException {
        List<HistoryEntry> history = executeCall(
                getTrakt().users().history(TestData.USER_SLUG, 1,
                        DEFAULT_PAGE_SIZE, null,
                        null, null));
        for (HistoryEntry entry : history) {
            assertThat(entry.getId()).isGreaterThan(0);
            assertThat(entry.getWatched_at()).isNotNull();
            assertThat(entry.getAction()).isNotEmpty();
            assertThat(entry.getType()).isNotEmpty();
            if ("episode".equals(entry.getType())) {
                assertThat(entry.getEpisode()).isNotNull();
                assertThat(entry.getShow()).isNotNull();
            } else if ("movie".equals(entry.getType())) {
                assertThat(entry.getMovie()).isNotNull();
            }
        }
    }

    @Test
    public void test_historyEpisodes() throws IOException {
        List<HistoryEntry> history = executeCall(
                getTrakt().users().history(TestData.USER_SLUG, HistoryType.EPISODES, 1,
                        DEFAULT_PAGE_SIZE, null,
                        null, null));
        for (HistoryEntry entry : history) {
            assertThat(entry.getId()).isGreaterThan(0);
            assertThat(entry.getWatched_at()).isNotNull();
            assertThat(entry.getAction()).isNotEmpty();
            assertThat(entry.getType()).isEqualTo("episode");
            assertThat(entry.getEpisode()).isNotNull();
            assertThat(entry.getShow()).isNotNull();
            System.out.println(
                    "Episode watched at date: " + entry.getWatched_at() + entry.getWatched_at().toInstant().toEpochMilli());
        }
    }

    @Test
    public void test_historyMovies() throws IOException {
        List<HistoryEntry> history = executeCall(
                getTrakt().users().history(UserSlug.Companion.getME(), HistoryType.MOVIES, 1,
                        DEFAULT_PAGE_SIZE, null,
                        null, null));
        assertMovieHistory(history);
    }

    @Test
    public void test_historyItem() throws IOException {
        List<HistoryEntry> history = executeCall(getTrakt().users().history(UserSlug.Companion.getME(), HistoryType.MOVIES,
                TestData.MOVIE_WATCHED_TRAKT_ID, 1,
                DEFAULT_PAGE_SIZE, null,
                OffsetDateTime.of(2016, 8, 3, 9, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2016, 8, 3, 10, 0, 0, 0, ZoneOffset.UTC)));
        assertThat(history.size()).isGreaterThan(0);
        assertMovieHistory(history);
    }

    private void assertMovieHistory(List<HistoryEntry> history) {
        for (HistoryEntry entry : history) {
            assertThat(entry.getWatched_at()).isNotNull();
            assertThat(entry.getAction()).isNotEmpty();
            assertThat(entry.getType()).isEqualTo("movie");
            assertThat(entry.getMovie()).isNotNull();
        }
    }

    @Test
    public void test_ratingsMovies() throws IOException {
        List<RatedMovie> ratedMovies = executeCall(
                getTrakt().users().ratingsMovies(TestData.USER_SLUG, RatingsFilter.ALL,
                        null));
        assertRatedEntities(ratedMovies);
    }

    @Test
    public void test_ratingsMovies_filtered() throws IOException {
        List<RatedMovie> ratedMovies = executeCall(getTrakt().users().ratingsMovies(TestData.USER_SLUG,
                RatingsFilter.TOTALLYNINJA,
                null));
        for (RatedMovie movie : ratedMovies) {
            assertThat(movie.getRated_at()).isNotNull();
            assertThat(movie.getRating()).isEqualTo(Rating.TOTALLYNINJA);
        }
    }

    @Test
    public void test_ratingsShows() throws IOException {
        List<RatedShow> ratedShows = executeCall(
                getTrakt().users().ratingsShows(TestData.USER_SLUG, RatingsFilter.ALL,
                        null));
        assertRatedEntities(ratedShows);
    }

    @Test
    public void test_ratingsSeasons() throws IOException {
        List<RatedSeason> ratedSeasons = executeCall(
                getTrakt().users().ratingsSeasons(TestData.USER_SLUG, RatingsFilter.ALL,
                        null));
        assertRatedEntities(ratedSeasons);
    }

    @Test
    public void test_ratingsEpisodes() throws IOException {
        List<RatedEpisode> ratedEpisodes = executeCall(
                getTrakt().users().ratingsEpisodes(TestData.USER_SLUG, RatingsFilter.ALL,
                        null));
        assertRatedEntities(ratedEpisodes);
    }

    @Test
    public void test_watchlistMovies() throws IOException {
        List<BaseMovie> movies = executeCall(getTrakt().users().watchlistMovies(UserSlug.Companion.getME(),
                null));
        assertSyncMovies(movies, "watchlist");
    }

    @Test
    public void test_watchlistShows() throws IOException {
        List<BaseShow> shows = executeCall(getTrakt().users().watchlistShows(UserSlug.Companion.getME(),
                null));
        for (BaseShow show : shows) {
            assertThat(show.getShow()).isNotNull();
            assertThat(show.getListed_at()).isNotNull();
        }
    }

    @Test
    public void test_watchlistSeasons() throws IOException {
        List<WatchlistedSeason> seasons = executeCall(getTrakt().users().watchlistSeasons(UserSlug.Companion.getME(),
                null));
        for (WatchlistedSeason season : seasons) {
            assertThat(season.getSeason()).isNotNull();
            assertThat(season.getShow()).isNotNull();
            assertThat(season.getListed_at()).isNotNull();
        }
    }

    @Test
    public void test_watchlistEpisodes() throws IOException {
        List<WatchlistedEpisode> episodes = executeCall(getTrakt().users().watchlistEpisodes(UserSlug.Companion.getME(),
                null));
        for (WatchlistedEpisode episode : episodes) {
            assertThat(episode.getEpisode()).isNotNull();
            assertThat(episode.getShow()).isNotNull();
            assertThat(episode.getListed_at()).isNotNull();
        }
    }


    @Test
    public void test_watchedMovies() throws IOException {
        List<BaseMovie> watchedMovies = executeCall(getTrakt().users().watchedMovies(TestData.USER_SLUG,
                null));
        assertSyncMovies(watchedMovies, "watched");
    }

    @Test
    public void test_watchedShows() throws IOException {
        List<BaseShow> watchedShows = executeCall(getTrakt().users().watchedShows(TestData.USER_SLUG,
                null));
        assertSyncShows(watchedShows, "watched");
    }

}
