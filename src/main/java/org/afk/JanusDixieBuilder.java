/**
 * Created by axel on 28.10.15.
 */
package org.afk;

import org.afk.fallback.DefaultJaDiManipulator;
import org.afk.fallback.DefaultJaDiMemory;
import org.afk.fallback.DefaultJaDiPersistence;

import java.util.Optional;

/**
 * This Builder is used to initialize a JanusDixie.
 * The {@see JanusDixie} is the central point of user configurable data.
 * It combines <br>
 * the {@see JaDiMemory}, that is used to store the data in memory,
 * the {@see JaDiPersistence}, that stores the data persistently and
 * the {@see JaDiManipulator}, that is the outside interface that manipulates the data.
 * <p>
 * JanusDixie dixie = new JanusDixieBuilder()
 * .setMemory(myDixieContainer)
 * .setPersistence(myDixieStorage)
 * .setManipulator(myModifier)
 * .create();
 * <p>
 * <p>
 * If no JaDiMemory is set, the {@see org.afk.fallback.DefaultJaDiMemory} is used.
 * If no JaDiPersistence is set, the {@see org.afk.fallback.DefaultJaDiPersistence} is used.
 * If no JaDiManipulator is set, the {@see org.afk.fallback.DefaultJaDiManipulator} is used.
 */
public class JanusDixieBuilder {

    private JaDiMemory memory;
    private JaDiPersistence persistence;
    private JaDiManipulator manipulator;


    public JanusDixieBuilder setMemory(JaDiMemory memory) {
        this.memory = memory;
        return this;
    }

    public JanusDixieBuilder setPersistence(JaDiPersistence persistence) {
        this.persistence = persistence;
        return this;
    }

    public JanusDixieBuilder setManipulator(JaDiManipulator manipulator) {
        this.manipulator = manipulator;
        return this;
    }

    public JanusDixie create() {
        return new JanusDixie(
                Optional.ofNullable(memory).orElse(new DefaultJaDiMemory()),
                Optional.ofNullable(persistence).orElse(new DefaultJaDiPersistence()),
                Optional.ofNullable(manipulator).orElse(new DefaultJaDiManipulator())
        );
    }
}
