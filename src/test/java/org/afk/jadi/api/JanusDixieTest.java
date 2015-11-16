package org.afk.jadi.api;

import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by axel on 05.11.15.
 */
public class JanusDixieTest {

    public static final String BAMAN_PIDERMAN = "Baman & Piderman";


    private JaDiRecordSet<String> rs;


    @Test
    public void nonNullParametersAreAccepted() {
        new JanusDixie(mock(JaDiMemory.class), mock(JaDiPersistence.class), mock(JaDiManipulator.class));
    }

    @Test(expected = NullPointerException.class)
    public void nullMemoryParameterIsNotAccepted() {
        new JanusDixie(null, mock(JaDiPersistence.class), mock(JaDiManipulator.class));
    }

    @Test(expected = NullPointerException.class)
    public void nullPersistenceParameterIsNotAccepted() {
        new JanusDixie(mock(JaDiMemory.class), null, mock(JaDiManipulator.class));
    }

    @Test(expected = NullPointerException.class)
    public void nullManipulatorParameterIsNotAccepted() {
        new JanusDixie(mock(JaDiMemory.class), mock(JaDiPersistence.class), null);
    }
}