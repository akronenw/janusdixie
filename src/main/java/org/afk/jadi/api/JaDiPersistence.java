package org.afk.jadi.api;

/**
 * The JaDiPersistence represents the persistent data storage that stores and
 * provides the configuration values between run times. Created by axel on
 * 28.10.15.
 */
public interface JaDiPersistence {

    /**
     * Retrieves the stored value from the underlying persistence layer.
     *
     * @param <T> The type of the stored value.
     * @param id The id of the stored value.
     * @param clazz The class of the value.
     * @return the stored value or {@code null} if not available.
     */
    <T> T retrieve(String id, Class<T> clazz);

    /**
     * Stores the value in the underlying persistence layer.
     *
     * @param id The id of the stored value.
     * @param t The value to be stored.
     * @param <T> The type of the value to be stored.
     * @param clazz The class of the value.
     * @return the previously stored value.
     */
    <T> T store(String id, T t, Class<T> clazz);

    /**
     * Retrieves if a stored value exists for this id.
     *
     * @param id The id of the stored value.
     * @return {@code true} if the value is available, {@code false} otherwise.
     */
    boolean has(String id);
}
