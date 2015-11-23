package org.afk.jadi.impl.persist.prefs;

import org.afk.jadi.api.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * The JaDiPreferencePersistence uses a Preferences Object to store a T value under a String key. It uses a JaDiStringConverterFactory for converting the value to String.
 * Created by axel on 08.11.2015.
 */
public class JaDiPreferencePersistence implements JaDiPersistence {

    private final Preferences prefs;
    private final JaDiStringConverterFactory factory;

    public JaDiPreferencePersistence(Preferences prefs, JaDiStringConverterFactory factory) {
        this.prefs = prefs;
        this.factory = factory;
    }


    @Override
    public <T> T retrieve(String id, Class<T> clazz) {
        final JaDiStringConverter<T> converterForClass = factory.getConverterForClass(clazz);
        final String s = prefs.get(id, null);
        return converterForClass.parse(s);
    }

    @Override
    public <T> void store(String id, T t) {
        if (t == null)
            prefs.put(id, null);
        else {
            final JaDiStringConverter<T> converter = factory.getConverterForClass((Class<T>) t.getClass());
            prefs.put(id, converter.format(t));
        }
    }

    @Override
    public boolean has(String id) {
        try {
            return new HashSet<>(Arrays.asList(prefs.keys())).contains(id);
        } catch (BackingStoreException e) {
            throw new JaDiException(e, JaDiError.SYSTEM);
        }
    }
}
