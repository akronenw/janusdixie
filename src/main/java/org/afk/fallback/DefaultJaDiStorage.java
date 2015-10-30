package org.afk.fallback;

/**
 * The Default JaDiStorage drops all data and returns null on every request.
 * Created by axel on 28.10.15.
 */
public class DefaultJaDiStorage implements org.afk.JaDiStorage {

    /**
     * Returns always null.
     *
     * @param id  The ID of the requested value.
     * @param <T> The type of the requested value.
     * @return null.
     */
    public <T> T retrieve(String id) {
        return null;
    }

    /**
     * Drops the va<lue
     *
     * @param id  The ID of the requested value.
     * @param t   The dropped value.
     * @param <T> The type of the requested value.
     */
    public <T> void store(String id, T t) {
    }
}
