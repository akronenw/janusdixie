package org.afk.jadi.api;

import java.util.function.Supplier;

/**
 * The JaDiMemory represents the in memory cache that holds the provided configuration {@link JaDiRecordSet}s during runtime.
 * Created by axel on 28.10.15.
 */
public interface JaDiMemory {

    /**
     * Retrieves the stored {@link JaDiRecordSet} or uses the factory to create and store the {@link JaDiRecordSet}.
     *
     * @param id               The id of the configuration {@link JaDiRecordSet}.
     * @param recordSetFactory The factory to create the {@link JaDiRecordSet}.
     * @param <T>              The type of the config value in the {@link JaDiRecordSet}.
     * @return the stored or created {@link JaDiRecordSet}.
     */
    <T> JaDiRecordSet<T> getOrCreate(String id, Supplier<JaDiRecordSet<T>> recordSetFactory);

    /**
     * Retrieves the stored {@link JaDiRecordSet}.
     *
     * @param id  The id of the configuration {@link JaDiRecordSet}.
     * @param <T> The type of the config value in the {@link JaDiRecordSet}.
     * @return the stored {@link JaDiRecordSet} or null.
     */
    <T> JaDiRecordSet<T> get(String id);

    /**
     * Retrieves if a {@link JaDiRecordSet} exists for this id.
     *
     * @param id The id of the configuration {@link JaDiRecordSet}.
     * @return {@code true} if the {@link JaDiRecordSet} is available, {@code false} otherwise.
     */
    boolean has(String id);
}
