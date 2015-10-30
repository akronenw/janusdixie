/**
 * Created by axel on 28.10.15.
 */
package org.afk;

/**
 * This Builder is used to initialize a JanusDixie.
 * The JanusDixie is the central point of user configurable data.
 * It combines <br>
 * the JaDiContainer, that is used to store the data in memory,
 * the JaDiStorage, that stores the data persistently and
 * the JaDiModifier, that is the interactive user interface.
 */
public class JanusDixieBuilder {

    private JaDiContainer container;
    private JaDiStorage storage;
    private JaDiModifier modifier;

    /**
     * Set the
     * @param container
     * @return
     */
    public JanusDixieBuilder setDixieContainer(JaDiContainer container) {
        this.container = container;
        return this;
    }

    public JanusDixieBuilder setDixieStorage(JaDiStorage storage) {
        this.storage = storage;
        return this;
    }

    public JanusDixieBuilder setJanusModifier(JaDiModifier modifier) {
        this.modifier = modifier;
        return this;
    }

    public JanusDixie create() {
        return new JanusDixie(container, storage, modifier);
    }
}
