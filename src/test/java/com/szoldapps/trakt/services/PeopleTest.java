package com.szoldapps.trakt.services;

import com.szoldapps.trakt.entities.Credits;
import com.szoldapps.trakt.enums.Extended;
import com.szoldapps.trakt.enums.Type;
import com.szoldapps.trakt.BaseTestCase;
import com.szoldapps.trakt.entities.Credits;
import com.szoldapps.trakt.entities.Person;
import com.szoldapps.trakt.enums.Extended;
import com.szoldapps.trakt.enums.Type;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class PeopleTest extends BaseTestCase {

    private static final String TEST_PERSON_SLUG = "bryan-cranston";

    @Test
    public void test_summary() throws IOException {
        Person person = executeCall(getTrakt().people().summary(TEST_PERSON_SLUG, Extended.FULL));
        assertThat(person).isNotNull();
        assertThat(person.getName()).isNotEmpty();
        assertThat(person.getIds()).isNotNull();
        assertThat(person.getIds().getTrakt()).isNotNull();
        assertThat(person.getIds().getSlug()).isNotNull();
    }

    @Test
    public void test_movieCredits() throws IOException {
        Credits credits = executeCall(getTrakt().people().movieCredits(TEST_PERSON_SLUG));
        assertCast(credits, Type.MOVIE);
        assertCrew(credits, Type.MOVIE);
    }

    @Test
    public void test_showCredits() throws IOException {
        Credits credits = executeCall(getTrakt().people().showCredits(TEST_PERSON_SLUG));
        assertCast(credits, Type.SHOW);
        assertCrew(credits, Type.SHOW);
    }

}
