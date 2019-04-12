package com.uwetrottmann.trakt5.services;

import com.uwetrottmann.trakt5.BaseTestCase;
import com.uwetrottmann.trakt5.TestData;
import com.uwetrottmann.trakt5.entities.Episode;
import com.uwetrottmann.trakt5.entities.Ratings;
import com.uwetrottmann.trakt5.entities.Season;
import com.uwetrottmann.trakt5.entities.Stats;
import com.uwetrottmann.trakt5.enums.Extended;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SeasonsTest extends BaseTestCase {

    @Test
    public void test_summary() throws IOException {
        List<Season> seasons = executeCall(getTrakt().seasons().summary(TestData.SHOW_SLUG,
        Extended.FULLEPISODES));
        assertThat(seasons).isNotNull();
        assertThat(seasons).hasSize(5);
        for (Season season : seasons) {
            assertThat(season).isNotNull();
            // must have at least trakt and tvdb id
            assertThat(season.getIds().getTrakt()).isPositive();
            if (season.getIds().getTvdb() != null) {
                assertThat(season.getIds().getTvdb()).isPositive();
            }
            assertThat(season.getTitle()).isNotNull();
            assertThat(season.getNetwork()).isNotNull();
            // seasons start at 0 for specials
            assertThat(season.getNumber()).isGreaterThanOrEqualTo(0);
            assertThat(season.getEpisode_count()).isPositive();
            assertThat(season.getAired_episodes()).isGreaterThanOrEqualTo(0);
            assertThat(season.getRating()).isBetween(0.0, 10.0);
            assertThat(season.getVotes()).isGreaterThanOrEqualTo(0);
            // episode details
            if (season.getNumber() == TestData.EPISODE_SEASON) {
                assertThat(season.getEpisodes()).isNotNull();
                assertThat(season.getEpisodes()).isNotEmpty();
                Episode firstEp = null;
                for (Episode episode : season.getEpisodes()) {
                    if (episode.getNumber() == TestData.EPISODE_NUMBER) {
                        firstEp = episode;
                        break;
                    }
                }
                assertThat(firstEp).isNotNull();
                assertThat(firstEp.getTitle()).isEqualTo(TestData.EPISODE_TITLE);
                assertThat(firstEp.getSeason()).isEqualTo(TestData.EPISODE_SEASON);
                assertThat(firstEp.getNumber()).isEqualTo(TestData.EPISODE_NUMBER);
                assertThat(firstEp.getIds().getImdb()).isEqualTo(TestData.EPISODE_IMDB_ID);
                assertThat(firstEp.getIds().getTmdb()).isEqualTo(TestData.EPISODE_TMDB_ID);
                assertThat(firstEp.getIds().getTvdb()).isEqualTo(TestData.EPISODE_TVDB_ID);
                assertThat(firstEp.getOverview()).isNotEmpty();
            }
        }
    }

    @Test
    public void test_season() throws IOException {
        List<Episode> season = executeCall(getTrakt().seasons().season(TestData.SHOW_SLUG, TestData.EPISODE_SEASON,
                null));
        assertThat(season).isNotNull();
        assertThat(season).isNotEmpty();
        for (Episode episode : season) {
            assertThat(episode.getSeason()).isEqualTo(TestData.EPISODE_SEASON);
        }
    }

    @Test
    public void test_comments() throws IOException {
        executeCall(getTrakt().seasons().comments(TestData.SHOW_SLUG, TestData.EPISODE_SEASON));
    }

    @Test
    public void test_ratings() throws IOException {
        Ratings ratings = executeCall(getTrakt().seasons().ratings(TestData.SHOW_SLUG, TestData.EPISODE_SEASON));
        assertRatings(ratings);
    }

    @Test
    public void test_stats() throws IOException {
        Stats stats = executeCall(getTrakt().seasons().stats(TestData.SHOW_SLUG, TestData.EPISODE_SEASON));
        assertShowStats(stats);
    }

}
