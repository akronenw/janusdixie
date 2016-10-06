package org.afk.jadi.impl.persist.prefs;

import org.afk.jadi.tools.SimpleStringConverterFactory;
import org.junit.After;
import org.junit.Test;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Created by axel on 23.11.15.
 */
public class JaDiPreferencePersistenceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private Preferences prefs;
    private JaDiPreferencePersistence jaDiPreferencePersistence;

    /**
     * Clean the preferences after testing.
     *
     * @throws BackingStoreException
     */
    @After
    public void removeNode() throws BackingStoreException {
        Preferences.userNodeForPackage(this.getClass()).removeNode();
    }

    /**
     * test that there is no stored value in fresh preference persistence.
     */
    @Test
    public void testPrefsHasNotUnsetValue() {
        givenPrefs();
        withPersistence();

        assertThat(jaDiPreferencePersistence.has("a"), not(equalTo(true)));
    }

    /**
     * test that a stored value is available.
     */
    @Test
    public void testPrefsHasStoredValue() {
        givenPrefs();
        withPersistence();
        jaDiPreferencePersistence.store("a", "b", String.class);

        assertThat(jaDiPreferencePersistence.has("a"), is(true));
    }

    /**
     * test that a stored string is retrieved.
     */
    @Test
    public void testPrefsProvideStoredValue() {
        givenPrefs();
        withPersistence();
        jaDiPreferencePersistence.store("a", "b", String.class);
        String result = jaDiPreferencePersistence.retrieve("a", String.class);
        assertThat(result, is("b"));
    }

    /**
     * test that a stored double is retrieved.
     */
    @Test
    public void testPrefsProvideStoredDoubleValue() {
        givenPrefs();
        withPersistence();
        final double t = 24.35;
        jaDiPreferencePersistence.store("a", t, Double.class);
        Double result = jaDiPreferencePersistence.retrieve("a", Double.class);
        assertThat(result, is(t));
    }

    /**
     * test that a converter for Object can not be retrieved.
     */
    @Test
    public void testPrefsProvideStoredObjectValue() {
        givenPrefs();
        withPersistence();
        final double t = 24.35;
        
        exception.expect(RuntimeException.class);
        exception.expectMessage("org.afk.jadi.api.JaDiException: No Converter for class class java.lang.Object defined");
        jaDiPreferencePersistence.store("a", t, Object.class);
    }

    private void withPersistence() {
        jaDiPreferencePersistence = new JaDiPreferencePersistence(prefs, new SimpleStringConverterFactory());
    }

    private void givenPrefs() {
        prefs = Preferences.userNodeForPackage(this.getClass());
    }
}
