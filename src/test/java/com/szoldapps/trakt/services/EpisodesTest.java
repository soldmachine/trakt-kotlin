package com.szoldapps.trakt.services;

import com.szoldapps.trakt.entities.Episode;
import com.szoldapps.trakt.enums.Extended;
import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.TestData;
import com.szoldapps.trakt.entities.Episode;
import com.szoldapps.trakt.entities.Ratings;
import com.szoldapps.trakt.entities.Stats;
import com.szoldapps.trakt.enums.Extended;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class EpisodesTest extends BaseTestCase {

    @Test
    public void test_summary() throws IOException {
        Episode episode = executeCall(getTrakt().episodes().summary(String.valueOf(TestData.SHOW_TRAKT_ID),
                TestData.EPISODE_SEASON,
                TestData.EPISODE_NUMBER, Extended.FULL));
        assertThat(episode).isNotNull();
        assertThat(episode.getTitle()).isEqualTo(TestData.EPISODE_TITLE);
        assertThat(episode.getSeason()).isEqualTo(TestData.EPISODE_SEASON);
        assertThat(episode.getNumber()).isEqualTo(TestData.EPISODE_NUMBER);
        assertThat(episode.getIds().getImdb()).isEqualTo(TestData.EPISODE_IMDB_ID);
        assertThat(episode.getIds().getTmdb()).isEqualTo(TestData.EPISODE_TMDB_ID);
        assertThat(episode.getIds().getTvdb()).isEqualTo(TestData.EPISODE_TVDB_ID);
        assertThat(episode.getRuntime()).isGreaterThan(0);
        assertThat(episode.getComment_count()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void test_comments() throws IOException {
        executeCall(getTrakt().episodes().comments(TestData.SHOW_SLUG, TestData.EPISODE_SEASON,
                TestData.EPISODE_NUMBER, 1, DEFAULT_PAGE_SIZE, null));
    }

    @Test
    public void test_ratings() throws IOException {
        Ratings ratings = executeCall(getTrakt().episodes().ratings(TestData.SHOW_SLUG, TestData.EPISODE_SEASON,
                TestData.EPISODE_NUMBER));
        assertRatings(ratings);
    }

    @Test
    public void test_stats() throws IOException {
        Stats stats = executeCall(
                getTrakt().episodes().stats(TestData.SHOW_SLUG, TestData.EPISODE_SEASON, TestData.EPISODE_NUMBER));
        assertStats(stats);
    }

}
