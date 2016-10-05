package org.afk.jadi.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.afk.jadi.JanusDixieBuilder;

/**
 * The JanusDixie class ist the central management class of the JanusDixie
 * library. It must be initialized with valid parameters and is responsible to
 * tie the different aspects of the modules logically together.It is recommended
 * to use the {
 *
 * @see JanusDixieBuilder} to generate a valid JanusDixie. (Version upgrades
 * might add new features and change the constructor of this class)
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
     * This method registers a callback for an ID. Whenever the value stored in
     * memory will change, the callback is called with the new Value. This
     * method throws an Exception, in case somebody has already registered a
     * callback. The setter will be called immediately with either the persisted
     * or the default value.
     *
     * @param id The unique ID where the value is registered.
     * @param setter The setter that will be called, when the value changes.
     * @param defaultProvider The default value supplier that will be called, if
     * the Value was not persisted before.
     * @param <T> The type of the value.
     * @param clazz The class of the value
     * @return The new RecordSet of the ID.
     * @throws JaDiException in case the ID was already registered.
     */
    public <T> JaDiRecordSet<T> addOrFail(String id, Consumer<T> setter, final Supplier<T> defaultProvider, Class<T> clazz) throws JaDiException {
        if (memory.has(id)) {
            throw new JaDiException("ID '" + id + "' exists ");
        }

        return addOrGet(id, setter, defaultProvider, clazz);
    }

    /**
     * This method registers a callback for an ID. Whenever the value stored in
     * memory will change, the callback is called with the new Value. If there
     * is already a registered callback, the previous JaDiRecordSet is returned.
     * The setter will be called immediately with either the persisted or the
     * default value.
     *
     * @param <T> The type of the value.
     * @param id The unique ID where the value is registered.
     * @param callback The callback that will be called, when the value changes.
     * @param defaultProvider The default value supplier that will be called, if
     * the Value was not persisted before.
     * @param clazz The class of the value.
     * @return The new or previous RecordSet of the ID.
     * @throws org.afk.jadi.api.JaDiException
     */
    public <T> JaDiRecordSet<T> addOrGet(String id, Consumer<T> callback, final Supplier<T> defaultProvider, Class<T> clazz) throws JaDiException {

        // get default or null
        final T aDefault = Optional.ofNullable(defaultProvider).orElse(() -> null).get();

        // get previous recordSet or create a new one
        JaDiRecordSet<T> recordSet = memory.getOrCreate(id, () -> new JaDiRecordSet<>(id, aDefault, callback));

        // read the persistence if exists
        if (persistence.has(id)) {
            recordSet.setAndFire(persistence.retrieve(id, clazz));
        }
        // register persistence for changes
        recordSet.addCallBack(value -> persistence.store(id, recordSet.getValue(), clazz));

        // fire initial value change
        recordSet.fireCallBacks();

        return recordSet;
    }

}
