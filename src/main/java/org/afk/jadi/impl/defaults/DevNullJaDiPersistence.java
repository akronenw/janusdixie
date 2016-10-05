package org.afk.jadi.impl.defaults;

import org.afk.jadi.api.JaDiPersistence;

/**
 * The DevNullJaDiPersistence drops all data and returns null on every request.
 * Created by axel on 28.10.15.
 */
public class DevNullJaDiPersistence implements JaDiPersistence {

    /**
     * Returns always null.
     *
     * @param <T> The type of the requested value.
     * @param id The ID of the requested value.
     * @param clazz
     * @return null.
     */
    @Override
    public <T> T retrieve(String id, Class<T> clazz) {
        return null;
    }

    /**
     * Discards the value.
     *
     * @param id The ID of the requested value.
     * @param t The dropped value.
     * @param <T> The type of the requested value.
     */
    @Override
    public <T> T store(String id, T t, Class<T> clazz) {
        return null;
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
