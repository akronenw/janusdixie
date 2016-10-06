package org.afk.jadi.defaults;

import org.afk.jadi.api.JaDiRecordSet;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author axel
 */
public class MappedJaDiMemoryTest {

    private MappedJaDiMemory memory;
    private String changed;

    /**
     * tests that the empty memory has no value.
     */
    @Test
    public void testMemoryHasNoValue() {
        givenMemory();
        assertThat(memory.has("a"), is(false));
    }

    /**
     * tests that the empty memory returns no value.
     */
    @Test
    public void testMemoryGetReturnsNoValue() {
        givenMemory();
        assertThat(memory.get("a"), is(nullValue()));
    }

    /**
     * tests that the empty memory returns no value.
     */
    @Test
    public void testMemoryGetOrCreateCreatesRecordSet() {
        givenMemory();
        JaDiRecordSet<String> jaDiRecordSet = new JaDiRecordSet<>("a", "Ape", this::changed);
        assertThat(memory.getOrCreate("a", () -> jaDiRecordSet), is(jaDiRecordSet));

        // callback is not used
        assertThat(changed, is(nullValue()));
    }

    /**
     * tests that the first recordset is returned.
     */
    @Test
    public void testMemoryGetOrCreateCreatesRecordSetOnlyOnce() {
        givenMemory();
        JaDiRecordSet<String> jaDiRecordSet1 = new JaDiRecordSet<>("a", "Ape", this::changed);
        assertThat(memory.getOrCreate("a", () -> jaDiRecordSet1), is(jaDiRecordSet1));

        JaDiRecordSet<String> jaDiRecordSet2 = new JaDiRecordSet<>("a", "Pop", this::changed);
        assertThat(memory.getOrCreate("a", () -> jaDiRecordSet2), is(jaDiRecordSet1));

        // callback is not used
        assertThat(changed, is(nullValue()));
    }

    private void givenMemory() {
        memory = new MappedJaDiMemory();
    }

    private void changed(String value) {
        this.changed = value;
    }
}
