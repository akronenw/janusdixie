package org.afk.jadi.impl.persist.prefs;

import org.afk.jadi.api.JaDiPersistence;

import java.util.prefs.Preferences;

/**
 * Created by axel on 08.11.2015.
 */
public class JaDiPreferencePersistence implements JaDiPersistence {

    private final Preferences prefs;

    public JaDiPreferencePersistence(Preferences prefs) {
        this.prefs = prefs;
    }


    @Override
    public <T> T retrieve(String id) {
        return null;
    }

    @Override
    public <T> void store(String id, T t) {

    }

    @Override
    public boolean has(String id) {
        return false;
    }
}
