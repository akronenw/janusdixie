package org.afk;

/**
 * Created by axel on 28.10.15.
 */
public interface JaDiPersistence {
    <T> T retrieve(String id);

    <T> void store(String id, T t);

    boolean has(String id);
}
