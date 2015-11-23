package org.afk.jadi.impl.defaults;

import org.afk.jadi.api.JaDiPersistence;

/**
 * The Default JaDiPersistence drops all data and returns null on every request.
 * Created by axel on 28.10.15.
 */
public class DefaultJaDiPersistence implements JaDiPersistence {

    /**
     * Returns always null.
     *
     * @param id    The ID of the requested value.
     * @param <T>   The type of the requested value.
     * @param clazz The class of the retrieved type.
     * @return null.
     */
    @Override
    public <T> T retrieve(String id, Class<T> clazz) {
        return null;
    }

    /**
     * Discards the value.
     *
     * @param id  The ID of the requested value.
     * @param t   The dropped value.
     * @param <T> The type of the requested value.
     */
    @Override
    public <T> void store(String id, T t) {
    }

    /**
     * Returns always false.
     *
     * @param id The ID of the requested value.
     * @return false.
     */
    @Override
    public boolean has(String id) {
        return false;
    }
}
