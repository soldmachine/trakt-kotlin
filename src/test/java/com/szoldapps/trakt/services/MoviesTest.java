package com.szoldapps.trakt.services;

import com.szoldapps.trakt.entities.Comment;
import com.szoldapps.trakt.entities.Credits;
import com.szoldapps.trakt.entities.Movie;
import com.szoldapps.trakt.enums.Extended;
import com.szoldapps.trakt.enums.Type;
import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.TestData;
import com.szoldapps.trakt.entities.Comment;
import com.szoldapps.trakt.entities.Credits;
import com.szoldapps.trakt.entities.Movie;
import com.szoldapps.trakt.entities.MovieTranslation;
import com.szoldapps.trakt.entities.Ratings;
import com.szoldapps.trakt.entities.Stats;
import com.szoldapps.trakt.entities.Translation;
import com.szoldapps.trakt.entities.TrendingMovie;
import com.szoldapps.trakt.enums.Extended;
import com.szoldapps.trakt.enums.Type;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MoviesTest extends BaseTestCase {

    @Test
    public void test_popular() throws IOException {
        List<Movie> movies = executeCall(getTrakt().movies().popular(2, null, null));
        assertThat(movies).isNotNull();
        assertThat(movies.size()).isLessThanOrEqualTo(DEFAULT_PAGE_SIZE);
        for (Movie movie : movies) {
            assertMovieNotNull(movie);
        }
    }

    @Test
    public void test_trending() throws IOException {
        List<TrendingMovie> movies = executeCall(getTrakt().movies().trending(1, null, null));
        assertThat(movies).isNotNull();
        assertThat(movies.size()).isLessThanOrEqualTo(DEFAULT_PAGE_SIZE);
        for (TrendingMovie movie : movies) {
            assertThat(movie.getWatchers()).isNotNull();
            assertMovieNotNull(movie.getMovie());
        }
    }

    private void assertMovieNotNull(Movie movie) {
        assertThat(movie.getTitle()).isNotEmpty();
        assertThat(movie.getIds()).isNotNull();
        assertThat(movie.getIds().getTrakt()).isNotNull();
        assertThat(movie.getYear()).isNotNull();
    }

    @Test
    public void test_summary_slug() throws IOException {
        Movie movie = executeCall(getTrakt().movies().summary(TestData.MOVIE_SLUG, Extended.FULL));
        assertTestMovie(movie);
    }

    @Test
    public void test_summary_trakt_id() throws IOException {
        Movie movie = executeCall(getTrakt().movies().summary(String.valueOf(TestData.MOVIE_TRAKT_ID),
                Extended.FULL));
        assertTestMovie(movie);
    }

    public static void assertTestMovie(Movie movie) {
        assertThat(movie).isNotNull();
        assertThat(movie.getIds()).isNotNull();
        assertThat(movie.getTitle()).isEqualTo(TestData.MOVIE_TITLE);
        assertThat(movie.getYear()).isEqualTo(TestData.MOVIE_YEAR);
        assertThat(movie.getIds().getTrakt()).isEqualTo(TestData.MOVIE_TRAKT_ID);
        assertThat(movie.getIds().getSlug()).isEqualTo(TestData.MOVIE_SLUG);
        assertThat(movie.getIds().getImdb()).isEqualTo(TestData.MOVIE_IMDB_ID);
        assertThat(movie.getIds().getTmdb()).isEqualTo(TestData.MOVIE_TMDB_ID);
    }

    @Test
    public void test_translations() throws IOException {
        List<MovieTranslation> translations = executeCall(getTrakt().movies().translations("batman-begins-2005"));
        assertThat(translations).isNotNull();
        for (Translation translation : translations) {
            assertThat(translation.getLanguage()).isNotEmpty();
        }
    }

    @Test
    public void test_translation() throws IOException {
        List<MovieTranslation> translations = executeCall(getTrakt().movies().translation("batman-begins-2005",
                "de"));
        assertThat(translations).isNotNull();
        // we know that Batman Begins has a German translation, otherwise this test would fail
        assertThat(translations).hasSize(1);
        assertThat(translations.get(0).getLanguage()).isEqualTo("de");
    }

    @Test
    public void test_comments() throws IOException {
        List<Comment> comments = executeCall(getTrakt().movies().comments(TestData.MOVIE_SLUG, 1, null,
                null));
        assertThat(comments).isNotNull();
        assertThat(comments.size()).isLessThanOrEqualTo(DEFAULT_PAGE_SIZE);
    }

    @Test
    public void test_people() throws IOException {
        Credits credits = executeCall(getTrakt().movies().people(TestData.MOVIE_SLUG));
        assertCast(credits, Type.PERSON);
        assertCrew(credits, Type.PERSON);
    }

    @Test
    public void test_ratings() throws IOException {
        Ratings ratings = executeCall(getTrakt().movies().ratings(TestData.MOVIE_SLUG));
        assertRatings(ratings);
    }

    @Test
    public void test_stats() throws IOException {
        Stats stats = executeCall(getTrakt().movies().stats(TestData.MOVIE_SLUG));
        assertStats(stats);
    }

}
