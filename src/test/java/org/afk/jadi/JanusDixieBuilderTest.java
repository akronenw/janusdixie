package org.afk.jadi;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by axel on 05.11.15.
 */
public class JanusDixieBuilderTest {

    @Test
    public void emptyDoesNotThrowException() {
        assertNotNull(new JanusDixieBuilder().create());
    }

    @Test
    public void nullManipulatorDoesNotThrowException() {
        assertNotNull(new JanusDixieBuilder().setManipulator(null).create());
    }

    @Test
    public void nullMemoryDoesNotThrowException() {
        assertNotNull(new JanusDixieBuilder().setMemory(null).create());
    }

    @Test
    public void nullPersistanceDoesNotThrowException() {
        assertNotNull(new JanusDixieBuilder().setPersistence(null).create());
    }
}