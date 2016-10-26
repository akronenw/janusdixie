package org.afk.jadi.api;

import org.afk.jadi.defaults.MappedJaDiMemory;
import org.afk.jadi.defaults.StaticJaDiManipulator;
import org.afk.jadi.tools.SimpleStringConverterFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;

import static org.mockito.Mockito.mock;

/**
 * Created by axel on 05.11.15.
 */
public class JanusDixieTest {

    public static final String BAMAN_PIDERMAN = "Baman & Piderman";

    private JaDiRecordSet<String> rs;

    private JaDiMemory memory;
    private JaDiPersistence persistence;
    private JaDiManipulator manipulator;
    private JanusDixie janusDixie;
    private String value;

    @Test
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void nonNullParametersAreAccepted() {
        new JanusDixie(mock(JaDiMemory.class), mock(JaDiPersistence.class), mock(JaDiManipulator.class));
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void nullMemoryParameterIsNotAccepted() {
        new JanusDixie(null, mock(JaDiPersistence.class), mock(JaDiManipulator.class));
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void nullPersistenceParameterIsNotAccepted() {
        new JanusDixie(mock(JaDiMemory.class), null, mock(JaDiManipulator.class));
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void nullManipulatorParameterIsNotAccepted() {
        new JanusDixie(mock(JaDiMemory.class), mock(JaDiPersistence.class), null);
    }

    @Test
    public void persistenceWins() throws JaDiException {
        givenDixie();
        whithPersistedValue("key", "beer");
        whenAddedConfig("key", "coffee");
        thenCallBackIs("beer");
    }

    @Test
    public void defaultWins() throws JaDiException {
        givenDixie();
        whithPersistedValue("i", "beer");
        whenAddedConfig("key", "coffee");
        thenCallBackIs("coffee");
    }

    @Test
    public void testInterface() throws JaDiException {
        givenDixie();
        whithPersistedValue("this.is.sparta", "lame");
        WeirdConfig impl = whenRegisteredInterface(WeirdConfig.class);
        assertThat(impl.getName(), is("lame"));
        assertThat(impl.getLongness(), is(190L));
        assertThat(impl.getWhiskey(), is(190.305));
    }

    private void thenCallBackIs(String expected) {
        assertThat(value, is(expected));
    }

    private JaDiRecordSet<String> whenAddedConfig(String key, String defaultValue) throws JaDiException {
        return janusDixie.addOrGet(key, this::callBack, () -> defaultValue, String.class);
    }

    private void givenDixie() {
        memory = new MappedJaDiMemory();
        persistence = mock(JaDiPersistence.class);
        manipulator = new StaticJaDiManipulator();
        janusDixie = new JanusDixie(memory, persistence, manipulator);
    }

    private void whithPersistedValue(String key, String value) {
        doReturn(true).when(persistence).has(eq(key));
        doReturn(value).when(persistence).retrieve(eq(key), eq(String.class));
    }

    private void callBack(String value) {
        this.value = value;
    }

    private <T> T whenRegisteredInterface(Class<T> aClass) throws JaDiException {
        return janusDixie.registerProxy(aClass, new SimpleStringConverterFactory());
    }

    private static interface WeirdConfig {

        @Configured(key = "this.is.sparta", defaultValue = "fantastic")
        String getName();

        @Configured(key = "this.is.long", defaultValue = "190")
        Long getLongness();

        @Configured(key = "make.it.double", defaultValue = "190.305")
        Double getWhiskey();

    }
}
