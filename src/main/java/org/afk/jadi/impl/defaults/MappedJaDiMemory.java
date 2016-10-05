package org.afk.jadi.impl.defaults;

import org.afk.jadi.api.JaDiMemory;
import org.afk.jadi.api.JaDiRecordSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The MappedJaDiMemory stores all JaDiRecordSets in a synchronized Map.
 * Created by axel on 28.10.15.
 */
public class MappedJaDiMemory implements JaDiMemory {

    private final Map<String, JaDiRecordSet> records = Collections.synchronizedMap(new HashMap<>());

    @Override
    public <T> JaDiRecordSet<T> getOrCreate(String id, Supplier<JaDiRecordSet<T>> recordSetFactory) {
        synchronized (records) {
            if (!records.containsKey(id))
                records.put(id, recordSetFactory.get());
            return records.get(id);
        }
    }

    @Override
    public <T> JaDiRecordSet<T> get(String id) {
        return records.get(id);
    }

    @Override
    public boolean has(String id) {
        return records.containsKey(id);
    }
}
