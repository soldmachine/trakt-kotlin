package com.szoldapps.trakt.services;

import com.szoldapps.trakt.entities.Movie;
import com.szoldapps.trakt.entities.Show;
import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.TestData;
import com.szoldapps.trakt.entities.Movie;
import com.szoldapps.trakt.entities.Show;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecommendationsTest extends BaseTestCase {

    @Test
    public void test_movies() throws IOException {
        List<Movie> movies = executeCall(getTrakt().recommendations().movies(null));
        assertThat(movies).isNotEmpty();
    }

    @Test
    public void test_dismissMovie() throws IOException {
        executeVoidCall(getTrakt().recommendations().dismissMovie(String.valueOf(TestData.MOVIE_TRAKT_ID)));
    }

    @Test
    public void test_shows() throws IOException {
        List<Show> shows = executeCall(getTrakt().recommendations().shows(null));
        assertThat(shows).isNotEmpty();
    }

    @Test
    public void test_dismissShow() throws IOException {
        executeVoidCall(getTrakt().recommendations().dismissShow(String.valueOf(TestData.SHOW_TRAKT_ID)));
    }

}
