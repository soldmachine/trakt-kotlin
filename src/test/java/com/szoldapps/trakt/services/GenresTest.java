package com.szoldapps.trakt.services;

import com.szoldapps.trakt.entities.Genre;
import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.entities.Genre;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GenresTest extends BaseTestCase {

    @Test
    public void test_genres_shows() throws IOException {
        List<Genre> genres = executeCall(getTrakt().genres().shows());
        assertGenres(genres);
    }

    @Test
    public void test_genres_movies() throws IOException {
        List<Genre> genres = executeCall(getTrakt().genres().movies());
        assertGenres(genres);
    }

    private void assertGenres(List<Genre> genres) {
        for (Genre genre : genres) {
            assertThat(genre.getName()).isNotEmpty();
            assertThat(genre.getSlug()).isNotEmpty();
        }
    }

}
