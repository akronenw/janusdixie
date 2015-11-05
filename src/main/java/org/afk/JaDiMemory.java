package org.afk;

import java.util.function.Supplier;

/**
 * Created by axel on 28.10.15.
 */
public interface JaDiMemory {

    <T> JaDiRecordSet<T> getOrCreate(String id, Supplier<JaDiRecordSet<T>> recordSetFactory);

    <T> JaDiRecordSet<T> get(String id);

    boolean has(String id);
}
