package org.afk.jadi.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * This is used to store the internal data of the configuration
 * <p>
 * Created by axel on 30.10.15.
 */
public class JaDiRecordSet<T> {
    private volatile T value;
    private final String id;
    private final List<Consumer<T>> callBacks = Collections.synchronizedList(new ArrayList<>());

    /**
     * Creates a JaDiRecordSet with an ID, a value and a callback for changed values.
     *
     * @param id       The id of the configuration.
     * @param value    The initial value of the configuration.
     * @param callBack The external callback that will notify the user about changes of the configuration.
     */
    public JaDiRecordSet(String id, T value, Consumer<T> callBack) {
        this.id = id;
        this.callBacks.add(callBack);
        setValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JaDiRecordSet<?> that = (JaDiRecordSet<?>) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(id, that.id) &&
                Objects.equals(callBacks, that.callBacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, id, callBacks);
    }

    /**
     * This will call all callBacks with the current value.
     */
    public void fireCallBacks() {
        callBacks.stream()
                .parallel()
                .forEach(callBack -> callBack.accept(value));
    }

    /**
     * Adds a callBack to the list of callbacks.
     *
     * @param callBack the new callback.
     */
    public void addCallBack(Consumer<T> callBack) {
        this.callBacks.add(callBack);
    }

    /**
     * Removes a callback from the list of callbacks.
     *
     * @param callBack the callback to bew removed.
     * @return {@code true} if the callback was removed.
     */
    public boolean removeCallBack(Consumer<T> callBack) {
        return callBacks.remove(callBack);
    }

    /**
     * Retrieves the current config value.
     *
     * @return The current config value.
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the new configValue and will call all callbacks if the value was changed.
     *
     * @param value The new value.
     */
    public void setAndFire(T value) {
        boolean differentValue = !Objects.equals(value, this.value);

        setValue(value);
        if (differentValue) {
            fireCallBacks();
        }
    }

    /**
     * Sets the new value.
     *
     * @param value The new value.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Retrieves the ID of the recordSet.
     *
     * @return The ID.
     */
    public String getId() {
        return id;
    }
}
