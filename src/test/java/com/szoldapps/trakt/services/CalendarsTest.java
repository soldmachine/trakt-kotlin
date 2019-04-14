package com.szoldapps.trakt.services;

import com.szoldapps.trakt.entities.CalendarMovieEntry;
import com.szoldapps.trakt.entities.CalendarShowEntry;
import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.entities.CalendarMovieEntry;
import com.szoldapps.trakt.entities.CalendarShowEntry;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalendarsTest extends BaseTestCase {

    // week which has show premiere (and therefore season premiere)
    private static final String START_DATE_ALL = "2016-10-01";
    private static final String START_DATE_MY_SHOWS = "2014-09-01";
    private static final int TEST_DAYS = 7;
    public static final String START_DATE_MY_MOVIES = "2014-05-01";
    public static final int DAYS_MOVIES = 30;

    @Test
    public void test_myShows() throws IOException {
        List<CalendarShowEntry> entries = executeCall(getTrakt().calendars().myShows(START_DATE_MY_SHOWS, TEST_DAYS));
        assertShowCalendar(entries);
    }

    @Test
    public void test_myNewShows() throws IOException {
        List<CalendarShowEntry> response = executeCall(getTrakt().calendars().myNewShows(START_DATE_MY_SHOWS,
                TEST_DAYS));
        assertShowCalendar(response);
    }

    @Test
    public void test_mySeasonPremieres() throws IOException {
        List<CalendarShowEntry> response = executeCall(getTrakt().calendars().mySeasonPremieres(START_DATE_MY_SHOWS,
                TEST_DAYS));
        assertShowCalendar(response);
    }

    @Test
    public void test_myMovies() throws IOException {
        List<CalendarMovieEntry> response = executeCall(getTrakt().calendars().myMovies(START_DATE_MY_MOVIES,
                DAYS_MOVIES));
        assertMovieCalendar(response);
    }

    @Test
    public void test_shows() throws IOException {
        // do unauthenticated call
        getTrakt().accessToken(null);

        List<CalendarShowEntry> response = executeCall(getTrakt().calendars().shows(START_DATE_ALL, TEST_DAYS));
        assertShowCalendar(response);

        // restore auth
        getTrakt().accessToken(TEST_ACCESS_TOKEN);
    }

    @Test
    public void test_newShows() throws IOException {
        // do unauthenticated call
        getTrakt().accessToken(null);

        List<CalendarShowEntry> response = executeCall(getTrakt().calendars().newShows(START_DATE_ALL,
                TEST_DAYS));
        assertShowCalendar(response);

        // restore auth
        getTrakt().accessToken(TEST_ACCESS_TOKEN);
    }

    @Test
    public void test_seasonPremieres() throws IOException {
        // do unauthenticated call
        getTrakt().accessToken(null);

        List<CalendarShowEntry> response = executeCall(getTrakt().calendars().seasonPremieres(START_DATE_ALL,
                TEST_DAYS));
        assertShowCalendar(response);

        // restore auth
        getTrakt().accessToken(TEST_ACCESS_TOKEN);
    }

    @Test
    public void test_movies() throws IOException {
        // do unauthenticated call
        getTrakt().accessToken(null);

        List<CalendarMovieEntry> response = executeCall(getTrakt().calendars().movies(START_DATE_ALL, 30));
        assertMovieCalendar(response);

        // restore auth
        getTrakt().accessToken(TEST_ACCESS_TOKEN);
    }

    private void assertShowCalendar(List<CalendarShowEntry> shows) {
        for (CalendarShowEntry entry : shows) {
            assertThat(entry.getFirst_aired()).isNotNull();
            assertThat(entry.getEpisode()).isNotNull();
            assertThat(entry.getShow()).isNotNull();
        }
    }

    private void assertMovieCalendar(List<CalendarMovieEntry> movies) {
        for (CalendarMovieEntry entry : movies) {
            assertThat(entry.getReleased()).isNotNull();
            assertThat(entry.getMovie()).isNotNull();
        }
    }

}
