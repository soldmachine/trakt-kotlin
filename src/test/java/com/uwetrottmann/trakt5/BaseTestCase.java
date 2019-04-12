package com.uwetrottmann.trakt5;

import com.uwetrottmann.trakt5.entities.BaseEpisode;
import com.uwetrottmann.trakt5.entities.BaseMovie;
import com.uwetrottmann.trakt5.entities.BaseRatedEntity;
import com.uwetrottmann.trakt5.entities.BaseSeason;
import com.uwetrottmann.trakt5.entities.BaseShow;
import com.uwetrottmann.trakt5.entities.CastMember;
import com.uwetrottmann.trakt5.entities.Credits;
import com.uwetrottmann.trakt5.entities.CrewMember;
import com.uwetrottmann.trakt5.entities.Ratings;
import com.uwetrottmann.trakt5.entities.Stats;
import com.uwetrottmann.trakt5.entities.TraktError;
import com.uwetrottmann.trakt5.enums.Type;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.BeforeClass;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BaseTestCase {

    protected static final String TEST_CLIENT_ID = "35a671df22d3d98b09aab1c0bc52977e902e696a7704cab94f4d12c2672041e4";
    public static final String TEST_ACCESS_TOKEN = "3d0b1e1a0b7bf0925e16c45ccbcaa55a1b70757735fabf2f56aa7f8e6cfcde57"; // "sgtest" on production

    private static final boolean DEBUG = true;

    private static final TraktV2 trakt = new TestTraktV2(TEST_CLIENT_ID);
    protected static final Integer DEFAULT_PAGE_SIZE = 10;

    static class TestTraktV2 extends TraktV2 {

        public TestTraktV2(String apiKey) {
            super(apiKey);
        }

        public TestTraktV2(String apiKey, String clientSecret, String redirectUri) {
            super(apiKey, clientSecret, redirectUri);
        }

        @Override
        protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
            super.setOkHttpClientDefaults(builder);
            if (DEBUG) {
                // add logging
                // standard output is easier to read
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(System.out::println);
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
            }
        }
    }

    @BeforeClass
    public static void setUpOnce() {
        trakt.accessToken(TEST_ACCESS_TOKEN);
    }

    protected TraktV2 getTrakt() {
        return trakt;
    }

    /**
     * Execute call with non-Void response body.
     */
    public <T> T executeCall(Call<T> call) throws IOException {
        Response<T> response = call.execute();
        if (!response.isSuccessful()) {
            handleFailedResponse(response); // will throw error
        }
        T body = response.body();
        if (body != null) {
            return body;
        } else {
            throw new IllegalStateException("Body should not be null for successful response");
        }
    }

    /**
     * Execute call with Void response body.
     */
    public <T> void executeVoidCall(Call<T> call) throws IOException {
        Response<T> response = call.execute();
        if (!response.isSuccessful()) {
            handleFailedResponse(response); // will throw error
        }
    }

    public void assertSuccessfulResponse(Response response) {
        if (!response.isSuccessful()) {
            handleFailedResponse(response);
        }
    }

    private void handleFailedResponse(Response response) {
        if (response.code() == 401) {
            fail("Authorization required, supply a valid OAuth access token: "
                    + response.code() + " " + response.message());
        } else {
            String message = "Request failed: " + response.code() + " " + response.message();
            TraktError error = getTrakt().checkForTraktError(response);
            if (error != null && error.message != null) {
                message += " message: " + error.message;
            }
            fail(message);
        }
    }

    protected static <T extends BaseRatedEntity> void assertRatedEntities(List<T> ratedMovies) {
        for (BaseRatedEntity movie : ratedMovies) {
            assertThat(movie.getRated_at()).isNotNull();
            assertThat(movie.getRating()).isNotNull();
        }
    }

    public void assertRatings(Ratings ratings) {
        // rating can be null, but we use a show where we can be sure it's rated
        assertThat(ratings.rating).isGreaterThanOrEqualTo(0);
        assertThat(ratings.votes).isGreaterThanOrEqualTo(0);
        assertThat(ratings.distribution).hasSize(10);
    }

    public void assertStats(Stats stats) {
        assertThat(stats.watchers).isGreaterThanOrEqualTo(0);
        assertThat(stats.plays).isGreaterThanOrEqualTo(0);
        assertThat(stats.collectors).isGreaterThanOrEqualTo(0);
        assertThat(stats.comments).isGreaterThanOrEqualTo(0);
        assertThat(stats.lists).isGreaterThanOrEqualTo(0);
        assertThat(stats.votes).isGreaterThanOrEqualTo(0);
    }

    public void assertShowStats(Stats stats) {
        assertStats(stats);
        assertThat(stats.collected_episodes).isGreaterThanOrEqualTo(0);
    }

    protected static void assertSyncMovies(List<BaseMovie> movies, String type) {
        for (BaseMovie movie : movies) {
            assertThat(movie.getMovie()).isNotNull();
            switch (type) {
                case "collection":
                    assertThat(movie.getCollected_at()).isNotNull();
                    break;
                case "watched":
                    assertThat(movie.getPlays()).isPositive();
                    assertThat(movie.getLast_watched_at()).isNotNull();
                    break;
                case "watchlist":
                    assertThat(movie.getListed_at()).isNotNull();
                    break;
            }
        }
    }

    protected static void assertSyncShows(List<BaseShow> shows, String type) {
        for (BaseShow show : shows) {
            assertThat(show.getShow()).isNotNull();
            if ("collection".equals(type)) {
                assertThat(show.getLast_collected_at()).isNotNull();
            } else if ("watched".equals(type)) {
                assertThat(show.getPlays()).isPositive();
                assertThat(show.getLast_watched_at()).isNotNull();
            }

            for (BaseSeason season : show.getSeasons()) {
                assertThat(season.getNumber()).isGreaterThanOrEqualTo(0);
                for (BaseEpisode episode : season.getEpisodes()) {
                    assertThat(episode.getNumber()).isGreaterThanOrEqualTo(0);

                    if ("collection".equals(type)) {
                        assertThat(episode.getCollected_at()).isNotNull();
                    } else if ("watched".equals(type)) {
                        assertThat(episode.getPlays()).isPositive();
                        assertThat(episode.getLast_watched_at()).isNotNull();
                    }
                }
            }
        }
    }

    public void assertCast(Credits credits, Type type) {
        for (CastMember castMember : credits.getCast()) {
            assertThat(castMember.getCharacter()).isNotNull();
            if (type == Type.SHOW) {
                assertThat(castMember.getMovie()).isNull();
                assertThat(castMember.getShow()).isNotNull();
                assertThat(castMember.getPerson()).isNull();
            } else if (type == Type.MOVIE) {
                assertThat(castMember.getMovie()).isNotNull();
                assertThat(castMember.getShow()).isNull();
                assertThat(castMember.getPerson()).isNull();
            } else if (type == Type.PERSON) {
                assertThat(castMember.getMovie()).isNull();
                assertThat(castMember.getShow()).isNull();
                assertThat(castMember.getPerson()).isNotNull();
            }
        }
    }

    public void assertCrew(Credits credits, Type type) {
        if (credits.getCrew() != null) {
            assertCrewMembers(credits.getCrew().getProduction(), type);
            assertCrewMembers(credits.getCrew().getWriting(), type);
            assertCrewMembers(credits.getCrew().getDirecting(), type);
            assertCrewMembers(credits.getCrew().getCostumeAndMakeUp(), type);
            assertCrewMembers(credits.getCrew().getSound(), type);
            assertCrewMembers(credits.getCrew().getArt(), type);
            assertCrewMembers(credits.getCrew().getCamera(), type);
        }
    }

    public void assertCrewMembers(List<CrewMember> crew, Type type) {
        if (crew == null) {
            return;
        }
        for (CrewMember crewMember : crew) {
            assertThat(crewMember.getJob()).isNotNull(); // may be empty, so not checking for now
            if (type == Type.SHOW) {
                assertThat(crewMember.getMovie()).isNull();
                assertThat(crewMember.getShow()).isNotNull();
                assertThat(crewMember.getPerson()).isNull();
            } else if (type == Type.MOVIE) {
                assertThat(crewMember.getMovie()).isNotNull();
                assertThat(crewMember.getShow()).isNull();
                assertThat(crewMember.getPerson()).isNull();
            } else if (type == Type.PERSON) {
                assertThat(crewMember.getMovie()).isNull();
                assertThat(crewMember.getShow()).isNull();
                assertThat(crewMember.getPerson()).isNotNull();
            }
        }
    }

}
