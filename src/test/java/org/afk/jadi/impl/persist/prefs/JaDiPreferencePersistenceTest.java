package org.afk.jadi.impl.persist.prefs;

import org.afk.jadi.impl.defaults.DefaultJaDiStringConverterFactory;
import org.junit.After;
import org.junit.Test;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * Created by axel on 23.11.15.
 */
public class JaDiPreferencePersistenceTest {

    private Preferences prefs;
    private JaDiPreferencePersistence jaDiPreferencePersistence;

    @After
    public void removeNode() throws BackingStoreException {
        Preferences.userNodeForPackage(this.getClass()).removeNode();
    }

    @Test
    public void testPrefsHasNotUnsetValue() {
        givenPrefs();
        withPersistence();
        assertThat(jaDiPreferencePersistence.has("a"), not(equalTo(true)));
    }

    @Test
    public void testPrefsHasStoredValue() {
        givenPrefs();
        withPersistence();
        jaDiPreferencePersistence.store("a", "b");
        assertThat(jaDiPreferencePersistence.has("a"), equalTo(true));
    }

    @Test
    public void testPrefsProvideStoredValue() {
        givenPrefs();
        withPersistence();
        jaDiPreferencePersistence.store("a", "b");
        String result = jaDiPreferencePersistence.retrieve("a", String.class);
        assertThat(result, equalTo("b"));
    }

    @Test
    public void testPrefsProvideStoredDoubleValue() {
        givenPrefs();
        withPersistence();
        final double t = 24.35;
        jaDiPreferencePersistence.store("a", t);
        Double result = jaDiPreferencePersistence.retrieve("a", Double.class);
        assertThat(result, equalTo(t));
    }


    private void withPersistence() {
        jaDiPreferencePersistence = new JaDiPreferencePersistence(prefs, new DefaultJaDiStringConverterFactory());
    }

    private void givenPrefs() {
        prefs = Preferences.userNodeForPackage(this.getClass());
    }
}