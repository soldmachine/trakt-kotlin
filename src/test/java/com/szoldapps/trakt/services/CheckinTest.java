package com.szoldapps.trakt.services;

import com.szoldapps.trakt.entities.CheckinError;
import com.szoldapps.trakt.entities.EpisodeCheckin;
import com.szoldapps.trakt.entities.EpisodeCheckinResponse;
import com.szoldapps.trakt.entities.EpisodeIds;
import com.szoldapps.trakt.entities.MovieCheckin;
import com.szoldapps.trakt.entities.MovieCheckinResponse;
import com.szoldapps.trakt.entities.MovieIds;
import com.szoldapps.trakt.entities.Show;
import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.TestData;
import com.szoldapps.trakt.entities.CheckinError;
import com.szoldapps.trakt.entities.EpisodeCheckin;
import com.szoldapps.trakt.entities.EpisodeCheckinResponse;
import com.szoldapps.trakt.entities.EpisodeIds;
import com.szoldapps.trakt.entities.MovieCheckin;
import com.szoldapps.trakt.entities.MovieCheckinResponse;
import com.szoldapps.trakt.entities.MovieIds;
import com.szoldapps.trakt.entities.ShareSettings;
import com.szoldapps.trakt.entities.Show;
import com.szoldapps.trakt.entities.SyncEpisode;
import com.szoldapps.trakt.entities.SyncMovie;
import org.junit.Test;
import org.threeten.bp.OffsetDateTime;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.Nonnull;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


public class CheckinTest extends BaseTestCase {

    private static final String APP_VERSION = "trakt-java-4";
    private static final String APP_DATE = "2014-10-15";

    @Nonnull
    @Override
    public <T> T executeCall(@Nonnull Call<T> call) throws IOException {
        Response<T> response = call.execute();
        if (!response.isSuccessful()) {
            if (getTrakt().checkForCheckinError(response) != null) {
                fail("Check-in still in progress, may be left over from failed test");
            } else if (response.code() == 401) {
                fail("Authorization required, supply a valid OAuth access token: "
                        + response.code() + " " + response.message());
            } else {
                fail("Request failed: " + response.code() + " " + response.message());
            }
        }
        T body = response.body();
        if (body != null) {
            return body;
        } else {
            throw new IllegalStateException("Body should not be null for successful response");
        }
    }

    @Test
    public void test_checkin_episode() throws IOException {
        EpisodeCheckin checkin = buildEpisodeCheckin();

        EpisodeCheckinResponse response = executeCall(getTrakt().checkin().checkin(checkin));

        // delete check-in first
        test_checkin_delete();

        assertEpisodeCheckin(response);
    }

    @Test
    public void test_checkin_episode_without_ids() throws IOException {
        EpisodeCheckin checkin = buildEpisodeCheckinWithoutIds();

        EpisodeCheckinResponse response = executeCall(getTrakt().checkin().checkin(checkin));

        // delete check-in first
        test_checkin_delete();

        assertEpisodeCheckin(response);
    }

    private void assertEpisodeCheckin(EpisodeCheckinResponse response) {
        assertThat(response).isNotNull();
        // episode should be over in less than an hour
        assertThat(response.getWatched_at().isBefore(OffsetDateTime.now().plusHours(1))).isTrue();
        assertThat(response.getEpisode()).isNotNull();
        assertThat(response.getEpisode().getIds()).isNotNull();
        assertThat(response.getEpisode().getIds().getTrakt()).isEqualTo(TestData.EPISODE_TRAKT_ID);
        assertThat(response.getEpisode().getIds().getTvdb()).isEqualTo(TestData.EPISODE_TVDB_ID);
        assertThat(response.getShow()).isNotNull();
    }

    private static EpisodeCheckin buildEpisodeCheckin() {
        return new EpisodeCheckin.Builder(new SyncEpisode().id(EpisodeIds.Companion.tvdb(TestData.EPISODE_TVDB_ID)), APP_VERSION,
                APP_DATE)
                .message("This is a toasty episode!")
                .build();
    }

    private static EpisodeCheckin buildEpisodeCheckinWithoutIds() {
        Show show = new Show();
        show.setTitle(TestData.SHOW_TITLE);
        return new EpisodeCheckin.Builder(
                new SyncEpisode().season(TestData.EPISODE_SEASON).number(TestData.EPISODE_NUMBER), APP_VERSION,
                APP_DATE)
                .show(show)
                .message("This is a toasty episode!")
                .build();
    }

    @Test
    public void test_checkin_movie() throws IOException {
        MovieCheckin checkin = buildMovieCheckin();

        MovieCheckinResponse response = executeCall(getTrakt().checkin().checkin(checkin));
        assertThat(response).isNotNull();
        // movie should be over in less than 3 hours
        assertThat(response.getWatched_at().isBefore(OffsetDateTime.now().plusHours(3))).isTrue();
        MoviesTest.assertTestMovie(response.getMovie());

        test_checkin_delete();
    }

    private MovieCheckin buildMovieCheckin() {
        ShareSettings shareSettings = new ShareSettings();
        shareSettings.setFacebook(true);
        return new MovieCheckin.Builder(new SyncMovie().id(MovieIds.Companion.trakt(TestData.MOVIE_TRAKT_ID)), APP_VERSION,
                APP_DATE)
                .message("This is a toasty movie!")
                .sharing(shareSettings)
                .build();
    }

    @Test
    public void test_checkin_blocked() throws IOException {
        Checkin checkin = getTrakt().checkin();

        EpisodeCheckin episodeCheckin = buildEpisodeCheckin();
        EpisodeCheckinResponse response = executeCall(checkin.checkin(episodeCheckin));

        MovieCheckin movieCheckin = buildMovieCheckin();
        Response<MovieCheckinResponse> responseBlocked = checkin.checkin(movieCheckin).execute();
        if (responseBlocked.code() == 401) {
            fail("Authorization required, supply a valid OAuth access token: "
                    + responseBlocked.code() + " " + responseBlocked.message());
        }
        if (responseBlocked.code() != 409) {
            fail("Check-in was not blocked");
        }
        CheckinError checkinError = getTrakt().checkForCheckinError(responseBlocked);
        // episode check in should block until episode duration has passed
        assertThat(checkinError).isNotNull();
        assertThat(checkinError.getExpires_at()).isNotNull();
        assertThat(checkinError.getExpires_at().isBefore(OffsetDateTime.now().plusHours(1))).isTrue();

        // clean the check in
        test_checkin_delete();
    }


    @Test
    public void test_checkin_delete() throws IOException {
        // tries to delete a check in even if none active
        Response response = getTrakt().checkin().deleteActiveCheckin().execute();
        assertSuccessfulResponse(response);
        assertThat(response.code()).isEqualTo(204);
    }
}
