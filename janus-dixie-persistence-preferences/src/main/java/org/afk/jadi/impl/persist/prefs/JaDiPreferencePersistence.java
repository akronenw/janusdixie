package org.afk.jadi.impl.persist.prefs;

import org.afk.jadi.api.*;
import org.afk.jadi.tools.JaDiStringConverter;
import org.afk.jadi.tools.JaDiStringConverterFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * The JaDiPreferencePersistence uses a Preferences Object to store a T value
 * under a String key. It uses a JaDiStringConverterFactory for converting the
 * value to String. Created by axel on 08.11.2015.
 */
public class JaDiPreferencePersistence implements JaDiPersistence {

    private final Preferences preferences;
    private final JaDiStringConverterFactory factory;

    public JaDiPreferencePersistence(Preferences preferences, JaDiStringConverterFactory factory) {
        this.preferences = preferences;
        this.factory = factory;
    }

    @Override
    public <T> T retrieve(String id, Class<T> clazz) {
        final JaDiStringConverter<T> converterForClass;
        try {
            converterForClass = factory.<T>getConverterForClass(clazz);
        } catch (JaDiException ex) {
            throw new RuntimeException(ex);
        }
        final String s = preferences.get(id, null);
        return converterForClass.parse(s);
    }

    @Override
    public <T> T store(String id, T t, Class<T> clazz) {
        final JaDiStringConverter<T> converter;
        try {
            converter = factory.<T>getConverterForClass(clazz);
        } catch (JaDiException ex) {
            throw new RuntimeException(ex);
        }

        String previous = preferences.get(id, null);
        if (t == null) {
            preferences.put(id, null);
        } else {
            preferences.put(id, converter.format(t));
        }
        if (previous == null) {
            return null;
        }
        return converter.parse(previous);
    }

    @Override
    public boolean has(String id) {
        try {
            return new HashSet<>(Arrays.asList(preferences.keys())).contains(id);
        } catch (BackingStoreException e) {
            return false;
        }
    }
}
