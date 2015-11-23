package org.afk.jadi.api;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * The JanusDixie class ist the central management class of the JanusDixie library. It must be initialiced with valid paramaters and is responsible to
 * tie the different aspects of the modules logically together. doh! It is recommended to use the {@see JanusDixieBuilder} to generate a valid JanusDixie.
 * (Version upgrades might add new features and change the constructor of this class)
 * <p>
 * <p>
 * Created by axel on 28.10.15.
 */
public class JanusDixie {

    private final JaDiMemory memory;
    private final JaDiPersistence persistence;
    private final JaDiManipulator manipulator;

    /**
     * @param memory
     * @param persistence
     * @param manipulator
     */
    public JanusDixie(JaDiMemory memory, JaDiPersistence persistence, JaDiManipulator manipulator) {
        Objects.requireNonNull(memory, "memory can not be null");
        Objects.requireNonNull(persistence, "persistence can not be null");
        Objects.requireNonNull(manipulator, "manipulator can not be null");

        this.memory = memory;
        this.persistence = persistence;
        this.manipulator = manipulator;
    }

    /**
     * This method registers a callback for an ID. Whenever the value stored in memory will change,
     * the callback is called with the new Value.
     * This method throws an Exception, in case somebody has already registered a callback.
     * The setter will be called immediately with either the persisted or the default value.
     *
     * @param id              The unique ID where the value is registered.
     * @param setter          The setter that will be called, when the value changes.
     * @param defaultProvider The default value supplier that will be called, if the Value was not persisted before.
     * @param <T>             The type of the value.
     * @return The new RecordSet of the ID.
     * @throws JaDiException in case the ID was already registered.
     */
    public <T> JaDiRecordSet<T> addOrFail(String id, Consumer<T> setter, final Supplier<T> defaultProvider, Class<T> clazz) {
        if (memory.has(id))
            throw new JaDiException("ID '" + id + "' exists ", JaDiError.ID_EXISTS);

        return addOrGet(id, setter, defaultProvider, clazz);
    }


    /**
     * This method registers a callback for an ID. Whenever the value stored in memory will change,
     * the callback is called with the new Value. If there is already a registered callback, the previous JaDiRecordSet is returned.
     * The setter will be called immediately with either the persisted or the default value.
     *
     * @param id              The unique ID where the value is registered.
     * @param setter          The setter that will be called, when the value changes.
     * @param defaultProvider The default value supplier that will be called, if the Value was not persisted before.
     * @param <T>             The type of the value.
     * @return The new or previous RecordSet of the ID.
     */
    public <T> JaDiRecordSet<T> addOrGet(String id, Consumer<T> setter, final Supplier<T> defaultProvider, Class<T> clazz) {

        // get default or null
        final T aDefault = Optional.ofNullable(defaultProvider).orElse(() -> null).get();
        // get previous recordset or create a new one
        JaDiRecordSet<T> recordSet = memory.getOrCreate(id, () -> new JaDiRecordSet<T>(id, setter, aDefault,clazz));

        if (persistence.has(id))
            recordSet.setValue(persistence.retrieve(id, (Class<T>) recordSet.getValue().getClass()));
        else
            persistence.store(id, defaultProvider.get());

        updateCallbackWithChangedValue(recordSet, recordSet.getValue());

        return recordSet;
    }

    private <T> void updateCallbackWithChangedValue(JaDiRecordSet<T> recordSet, T newValue) {
        Consumer<T> setter = recordSet.getSetter();
        if (setter != null) {
            setter.accept(newValue);
        }
    }
}
