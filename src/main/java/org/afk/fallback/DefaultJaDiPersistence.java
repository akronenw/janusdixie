package org.afk.fallback;

import org.afk.JaDiPersistence;

/**
 * The Default JaDiPersistence drops all data and returns null on every request.
 * Created by axel on 28.10.15.
 */
public class DefaultJaDiPersistence implements JaDiPersistence {

    /**
     * Returns always null.
     *
     * @param id  The ID of the requested value.
     * @param <T> The type of the requested value.
     * @return null.
     */
    @Override
    public <T> T retrieve(String id) {
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